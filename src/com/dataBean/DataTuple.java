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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Neel Patel
 */
public class DataTuple implements IntDataTuple,Serializable{
    private static final long serialVersionUID = 1L;
    
    private String name="";
    private String ver="";
    private LocalDate date=null;
    
    static long getSerial(){
        return serialVersionUID;
    }
    
    DataTuple(String sName){
        this.name=sName;
    }
    
    DataTuple(String sName,String ver,LocalDate date){
        this(sName);
        this.date=date;
        this.ver=ver;
    }
    
    @Override
    public String getSoftName() {
        return name;
    }

    @Override
    public String getVersion() {
        return ver;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof DataTuple))
            return false;
        DataTuple c=(DataTuple) o;
        if(c.getSoftName().equals(this.getSoftName())&&
                  c.getDate().equals(this.getDate())&&
                  c.getVersion().equals(this.getVersion()))
                  return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.date);
        return hash;
    }
}
