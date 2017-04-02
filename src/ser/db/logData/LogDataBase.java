/*
 * Copyright 2017 Neel Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ser.db.logData;

import com.dataBean.DataBeans;
import com.dataBean.IntDataBean;
import com.dataBean.IntDataTuple;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ser.db.IntDataBase;
import ser.logger.LogTools;
import static ser.logger.LogTools.getLogProperty;

/**
 *
 * @author Neel Patel
 */
public class LogDataBase implements IntDataBase{
    private final Path dir;
    private static final String dtf="yyMMdd";
    
    public LogDataBase(Path d){
        dir=d;
    }

    @Override
    public List<String> getAllUserName(LocalDate date) {
        List<String> uName=readLog(date).stream()
                  .map(i->getLogProperty(i,"user"))
                  .filter(i->i!=null)
                  .distinct()
                  .collect(Collectors.toList());
        return uName;
    }
    
    @Override
    public List<IntDataBean> getAllUserDetail(LocalDate date) {
        try{
            List<String> l=readLog(date);
            Map<String,List<String>> m=new HashMap<>();
            l.forEach(x->{
                try{
                    String n=LogTools.getLogProperty(x,"user");
                    String s=LogTools.getLogProperty(n, "Software");
                    m.putIfAbsent(n,new ArrayList<>());
                    m.get(n).add(s);
                }catch(Exception ex){}
            });
            List<IntDataBean> ldb=new ArrayList<>();
            m.forEach((x,y)->{
                ldb.add(DataBeans.getDataBean(y, x,date));
            });
            return Collections.unmodifiableList(ldb);
        }catch(Exception ex){
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    @Override
    public List<IntDataBean> getAllUserDetail(LocalDate sd, LocalDate ed) {
        try{
            List<IntDataBean> li=new ArrayList<>();
            for(LocalDate d=sd;d.isBefore(ed);d=d.plusDays(1)){
                li.addAll(getAllUserDetail(d));
            }
            return Collections.unmodifiableList(li);
        }catch(Exception ex){
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    @Override
    public List<IntDataBean> getUserDetail(String user, LocalDate sd, LocalDate ed) {
        List<IntDataBean> ldb=new ArrayList<>();
        try{
            for(LocalDate d=sd;d.isBefore(ed);d=d.plusDays(1)){
                //System.out.println("check 1");
                ldb.addAll(getUserDetail(user,d));
            }
            return Collections.unmodifiableList(ldb);
        }catch(Exception ex){
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    @Override
    public List<IntDataBean> getSoftDetail(String soft, LocalDate sd, LocalDate ed) {
        List<IntDataBean> ldb=new ArrayList<>();
        try{
            for(LocalDate d=sd;d.isBefore(ed);d=d.plusDays(1)){
                ldb.addAll(getSoftDetail(soft,d));
            }
            return Collections.unmodifiableList(ldb);
        }catch(Exception ex){
            return Collections.unmodifiableList(new ArrayList<>());
        }
    }

    @Override
    public int removeUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeSoft(String soft) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeLogs(LocalDate ld) {
        try{
            Path pf=dir.resolve(ld
                      .format(DateTimeFormatter.ofPattern(dtf))+".txt");
            int i=Files.readAllLines(pf).size();
            if(Files.deleteIfExists(pf))
                return i;
            return 0;
        }catch(Exception ex){
            return -1;
        }
    }

    @Override
    public int removeLogsUpto(LocalDate ld) {
        for(LocalDate d=ld;;d=d.minusDays(1)){
            
        }
    }
    
    private List<String> readLog(LocalDate dt){
        try{
            Path pf=dir.resolve(dt
                      .format(DateTimeFormatter.ofPattern(dtf))+".txt");
            return Files.readAllLines(pf);
        }catch(Exception ex){
            return new ArrayList();
        }
    }
    
    private synchronized int removeLogs(List<String> oldLog,Path file) throws IOException{
        List<String> backLog=Files.readAllLines(file);
        try{
            List<String> allLog=Files.readAllLines(file);
            allLog.removeAll(oldLog);
            Files.deleteIfExists(file);
            Files.write(file, allLog);
            return backLog.size()-allLog.size();
        }catch(Exception ex){
            Files.deleteIfExists(file);
            Files.write(file, backLog);
            return -1;
        }
    }
    
    private List<IntDataBean> getUserDetail(String user,LocalDate ld){
        try{
            List<IntDataBean> ldb=new ArrayList<>();
            List<String> l=readLog(ld);
            Map<String,List<IntDataTuple>> m=new HashMap<>();
            String ip,mac;
            String lo=l.stream().filter(x->{
                    try{
                        return LogTools.getLogProperty(x,"user").equalsIgnoreCase(user);
                    }catch(Exception ex){
                        return false;
                    }
                })
                //.peek(x->System.out.println("check 2:-"+x))
                .peek(x->{
                    try{
                        String n=LogTools.getLogProperty(x,"user");
                        String s=LogTools.getLogProperty(x,"Software");
                        String v=LogTools.getLogProperty(x,"Version");
                        LocalDate d=null;
                        try{
                            d=LocalDate.parse(LogTools.getLogProperty(x,"InstallDate"));
                        }catch(Exception ex){}
                        m.putIfAbsent(n,new ArrayList<>());
                        IntDataTuple idt=DataBeans.getDataTuple(s,v,d);
                        if(!m.get(n).contains(idt))
                            m.get(n).add(idt);
                    }catch(Exception ex){}
                }).reduce((i,j)->i).orElse("");
            ip=LogTools.getLogProperty(lo, "ip");
            mac=LogTools.getLogProperty(lo, "mac");
            m.forEach((String x, List<IntDataTuple> y) -> {
                ldb.add(DataBeans.getAddDataBean(y,x
                          ,LocalDateTime.of(ld, LocalTime.now()),ip, mac));
            });
            return ldb;
        }catch(Exception ex){
            return new ArrayList<>();
        }
    }
    
    private List<IntDataBean> getSoftDetail(String soft,LocalDate ld){
        try{
            List<IntDataBean> ldb=new ArrayList<>();
            List<String> l=readLog(ld);
            Map<String,List<String>> m=new HashMap<>();
            l.stream().filter(x->{
                    try{
                        return LogTools.getLogProperty(x,"Software")
                                  .equalsIgnoreCase(soft);
                    }catch(Exception ex){
                        return false;
                    }
                }).forEach(x->{
                    try{
                        String n=LogTools.getLogProperty(x,"user");
                        String s=LogTools.getLogProperty(x,"Software");
                        m.putIfAbsent(n,new ArrayList<>());
                        m.get(n).add(s);
                    }catch(Exception ex){}
                });
            m.forEach((String x, List<String> y) -> {
                ldb.add(DataBeans.getDataBean(y, x, ld));
            });
            return ldb;
        }catch(Exception ex){
            return new ArrayList<>();
        }
    }

}
