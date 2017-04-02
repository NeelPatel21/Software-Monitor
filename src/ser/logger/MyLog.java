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
package ser.logger;

import com.dataBean.IntDataBean;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static ser.config.Configuration.getDefaultLogDir;

/**
 * this class provide the functionality to store logs in simple
   text formate.
 * this class manage the logs by the date.
 * @author Neel Patel
 */
public class MyLog implements IntLogger{
    private Path p;
    private static final String dtf="yyMMdd";
    
    public MyLog(){
        p=getDefaultLogDir();
    }
    
    public MyLog(Path dir){
        p=dir;
    }
    
    @Override
    public synchronized boolean log(IntDataBean db){
        try{
            LocalDate date=db.getTime().toLocalDate();
            Path pf=p.resolve(date
                      .format(DateTimeFormatter.ofPattern(dtf))+".txt");
            try(FileOutputStream out=new FileOutputStream(pf.toFile(),true);
                      PrintStream ps=new PrintStream(out)){
                if(ps==null)
                    return false;
                db.getSoftDetail().forEach(i->{
                    ps.println("Date = "+date.toString()+" ; "
                              +"User = "+db.getName()+" ; "
                              +"Software = "+i.getSoftName()+" ; "
                              +"Version = "+i.getVersion()+" ; "
                              +"Ip = "+db.getIP()+" ; "
                              +"Mac = "+db.getMac()+" ; "
                              +"Version = "+i.getVersion()+" ; "
                              +"InstallDate = "+i.getDate());
                    ps.flush();
                });
            }
            System.out.println("log :-"+db.getName()+" count :-"
                      +db.getSoftDetail().size());
            System.out.println("log return");
            return true;
        }catch(Exception ex){
            System.err.println("logging fail");
            return false;
        }
    }
    
}
