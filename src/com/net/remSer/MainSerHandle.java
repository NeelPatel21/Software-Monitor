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
package com.net.remSer;

import com.dataBean.IntDataBean;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import ser.logger.IntLogger;

/**
 *
 * @author Neel Patel
 */
public class MainSerHandle {
    private Supplier<String> keysp=()->"";
    private List<IntLogger> flog=new ArrayList<>();
    
    /**
     * this method return unique the key.
     * @param uName user name.
     * @return key as String.
     */
    public synchronized String getKey(String uName){
        try{
            System.out.println("get key check");
            return keysp.get();
        }catch(Exception ex){
            System.out.println("get key exception");
            return "";
        }
    }
    
    /**
     * this method register the logger.
     * the registered logger will be logged when the log arrived.
     * @param flog Object of type IntLogger.
     * @return true if successfully registered, false otherwise.
     */
    public synchronized boolean setLoger(IntLogger flog){
        this.flog.add(flog);
        return true;
    }
    
    /**
     * this method set the keySupplier.
     * @param sp keySupplier.
     * @return ture if successfully sat.
     */
    public synchronized boolean setKeysp(Supplier<String> sp){
        keysp=sp;
        return true;
    }
    
    /**
     * this method will call the log method of all the registered loggers.
     * @param db object of type IntDataBean.
     * @return true if all the registered logger have logged successfully.
     */
    public synchronized boolean log(IntDataBean db){
        try{
            System.out.println("log check");
            return flog.parallelStream().map(i->i.log(db)).allMatch(i->i==true);
        }catch(Exception ex){
            return false;
        }
    }
}
