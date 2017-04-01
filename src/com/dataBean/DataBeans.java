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
