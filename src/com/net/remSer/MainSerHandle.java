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
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author Neel Patel
 */
public class MainSerHandle {
    private Supplier<String> keysp=()->"";
    private Function<IntDataBean,Boolean> flog=x->false;
    
    public synchronized String getKey(String uName){
        try{
            System.out.println("get key check");
            return keysp.get();
        }catch(Exception ex){
            System.out.println("get key exception");
            return "";
        }
    }
    
    public synchronized boolean setLoger(Function<IntDataBean,Boolean> flog){
        this.flog=flog;
        return true;
    }
    
    public synchronized boolean setKeysp(Supplier<String> sp){
        keysp=sp;
        return true;
    }
    
    public synchronized boolean log(IntDataBean db){
        try{
            System.out.println("log check");
            return flog.apply(db);
        }catch(Exception ex){
            return false;
        }
    }
}
