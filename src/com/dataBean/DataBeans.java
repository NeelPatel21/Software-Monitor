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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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
       Object of LocalDateTime which shows the date and time when the
       object is created.
     * @param sd list of software name.
     * @param name user name.
     * @return Immutable object of IntDataBean.
     */
    public static IntDataBean getDataBean(List<String> sd,String name){
        return new DataBean(sd.stream().map(i->getDataTuple(i))
                  .collect(Collectors.toList()),name);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * @param sd List of Software details.
     * @param name user name.
     * @param ld object of type LocalDate.
     * @return immutable object of type IntDataBean.
     */
    public static IntDataBean getDataBean(List<String> sd,String name,LocalDate ld){
        return new DataBean(sd.stream().map(i->getDataTuple(i))
                  .collect(Collectors.toList()),name,LocalDateTime.of(ld, LocalTime.now()));
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the date and time when the
       object is created.
     * {@code getIP} and{@code getMac} methods will not work on the object
       return by this method.
     * @param sd List of IntDataTuple.
     * @param name user name.
     * @return immutable Object of type IntDataBean.
     */
    public static IntDataBean getNewDataBean(List<IntDataTuple> sd,String name){
        return new DataBean(sd,name);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * getTime method of the Object returned by this method will return
       Object of LocalDateTime which shows the date & time when the
       object is created.
     * {@code getIP} and{@code getMac} methods of the object return by
       this method will return the ip address and mac address of the pc
       where the object is created respectively.
     * @param sd list of IntDataTuples.
     * @param name user name.
     * @return immutable object of type IntDataBean.
     */
    public static IntDataBean getAddDataBean(List<IntDataTuple> sd,String name){
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return new DataBean(sd,name,LocalDateTime.now(),ip.getHostAddress(),sb.toString());
        } catch(Exception ex) {
            return null;
        }
    }
    
    /**
     * @param sd list of IntDataTuple.
     * @param name user name
     * @param dt object of LocaldateTime.
     * @param ip ip address. 
     * @param mac mac address.
     * @return object of type IntDataBean.
     */
    public static IntDataBean getAddDataBean(List<IntDataTuple> sd,String name,
              LocalDateTime dt,String ip,String mac){
        return new DataBean(sd,name,dt,ip,mac);
    }
    
    /**
     * returns immutable object of type IntDataBean.
     * @param sd list of the IntDataTuple.
     * @param name user name.
     * @param ld object of type LocalDate.
     * @return object of type IntDataBean.
     */
    public static IntDataBean getNewDataBean(List<IntDataTuple> sd,String name
              ,LocalDate ld){
        return new DataBean(sd,name,LocalDateTime.of(ld, LocalTime.now()));
    }
    
    /**
     * this method return tuple of type IntDataTuple which represent
       the information of any single software.
     * @param sName software name.
     * @param ver software version.
     * @param date installation date.
     * @return object of type IntDataTuple.
     */
    public static IntDataTuple getDataTuple(String sName,String ver,LocalDate date){
        return new DataTuple(sName,ver,date);
    }
    
    /**
     * this method map the software name to tuple.
     * the tuple returned by this software have only one detail which is name.
     * @param sName software name.
     * @return Object of type IntDataTuple.
     */
    public static IntDataTuple getDataTuple(String sName){
        return new DataTuple(sName,"",null);
    }
}
