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
package com.dataBean;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * this is factory class, provides functionality to manipulates Object of 
   IntDataBean.
 * @author Neel Patel
 */
public class DataBeans {
    private DataBeans(){}
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the information of 
     * @param sd
     * @param name
     * @return 
     */
    public static IntDataBean getDataBean(List<String> sd,String name){
        return new DataBean(sd.stream().map(i->getDataTuple(i))
                  .collect(Collectors.toList()),name);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the information of instalation date.
     * @param sd List of Software details.
     * @param name user name.
     * @param ld object of type LocalDate.
     * @return object of type IntDataBean.
     */
    public static IntDataBean getDataBean(List<String> sd,String name,LocalDate ld){
        return new DataBean(sd.stream().map(i->getDataTuple(i))
                  .collect(Collectors.toList()),name,LocalDateTime.of(ld, LocalTime.now()));
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the information of 
     * @param sd
     * @param name
     * @return 
     */
    public static IntDataBean getNewDataBean(List<IntDataTuple> sd,String name){
        return new DataBean(sd,name);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the information of 
     * @param sd
     * @param name
     * @return 
     */
    public static IntDataBean getAddDataBean(List<IntDataTuple> sd,String name){
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            String s="";
            for(byte i:mac){
                if(i<=9)
                    s+=i;
                else if(i==10)
                    s+='A';
                else if(i==11)
                    s+='B';
                else if(i==12)
                    s+='C';
                else if(i==13)
                    s+='D';
                else if(i==14)
                    s+='E';
                else if(i==15)
                    s+='F';
            }
            return new DataBean(sd,name,LocalDateTime.now(),ip.getHostAddress(),s);
        } catch(Exception ex) {
            return null;
        }
    }
    
    public static IntDataBean getAddDataBean(List<IntDataTuple> sd,String name,
              LocalDateTime dt,String ip,String mac){
        return new DataBean(sd,name,dt,ip,mac);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the information of 
     * @param sd
     * @param name
     * @param ld
     * @return 
     */
    public static IntDataBean getNewDataBean(List<IntDataTuple> sd,String name
              ,LocalDate ld){
        return new DataBean(sd,name,LocalDateTime.of(ld, LocalTime.now()));
    }
    
    public static IntDataTuple getDataTuple(String sName,String ver,LocalDate date){
        return new DataTuple(sName,ver,date);
    }
    
    public static IntDataTuple getDataTuple(String sName){
        return new DataTuple(sName,"",null);
    }
}
