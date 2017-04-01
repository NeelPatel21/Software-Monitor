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
package client.flow;

import client.filter.Filter;
import client.softData.SoftData;
import com.dataBean.DataBeans;
import static com.dataBean.DataBeans.getDataTuple;
import com.dataBean.IntDataBean;
import com.dataBean.IntDataTuple;
import com.net.remSer.IntMainSer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Neel Patel
 */
public class ClientFlow {
    private String url;
    public ClientFlow(String url){
        this.url=url;
    }
    
    public void start(){
        IntDataBean db;
        try{
            List<String> sd=new SoftData().start();
            //filter the data
            List<IntDataTuple> dt=new ArrayList(new Filter(sd).filterdata());
            db=DataBeans.getAddDataBean(dt,System.getenv("username"));
        }catch(Exception ex){
            System.err.println("error in getting software details :- "+ex);
            ex.printStackTrace();
            return;
        }
        
        String key=readKey();//read key
        System.out.println("key read :- "+key);
        if(key.trim().isEmpty()){//key is not available
            key=getKey();//get new key from server
            System.out.println("getting key :- "+key);
            if(key.trim().isEmpty())//check if the key is empty
                return;
            if(!writeKey(key))//write key to the hd.
                return;
        }
        
        if(log(db,url))
            return;
        
    }
    
    String readKey(){
        try {
            Path p=Paths.get(System.getenv("appdata"),"Software Monitor","key.txt");
            return Files.readAllLines(p).stream()
                      .filter(i->!i.trim().isEmpty()).findFirst().orElse("");
        } catch(Exception ex) {
            return "";
        }
    }
    
    boolean writeKey(String key){
        try {
            List<String> li=new ArrayList();
            li.add(key);
            Path p=Paths.get(System.getenv("appdata"),"Software Monitor");
            Files.createDirectories(p);
            p=p.resolve("key.txt");
            Files.write(p,li);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
    
    String getKey(){
        try {
            IntMainSer obj=(IntMainSer)Naming.lookup(url);
            String s=obj.getKey(System.getProperty("username"));
            if(!s.trim().isEmpty())
                return s;
        } catch(Exception ex) {
            System.out.println("error in getKey");
        }
        return "";
    }
    
    boolean log(IntDataBean db,String key){
        try{
            IntMainSer obj=(IntMainSer)Naming.lookup(url);
            return obj.log(db, key);
        }catch(Exception ex){
            return false;
        }
    }
}
