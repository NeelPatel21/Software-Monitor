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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
class DataBean implements IntDataBean{
    private static final long serialVersionUID = 2L;
    private LocalDateTime dt;
    private List<IntDataTuple> sd;
    private String name="",ip="",mac="";
    static long getVersion(){
        return serialVersionUID;
    }
    
    DataBean(List<IntDataTuple> softDat,String name){
        this(softDat,name,LocalDateTime.now());
    }
    
    DataBean(List<IntDataTuple> softDat,String name,LocalDateTime dt){
        this(softDat,name,dt,"","");
    }
    
    DataBean(List<IntDataTuple> softDat,String name,LocalDateTime dt,String ip,String mac){
        this.dt=dt;
        sd=new ArrayList<>();
        sd.addAll(softDat);
        this.name=name;
        this.ip=ip;
        this.mac=mac;
    }
    
    @Override
    public LocalDateTime getTime() {
        return dt;
    }

    @Override
    public List<IntDataTuple> getSoftDetail() {
        return Collections.unmodifiableList(sd);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public String getMac() {
        return mac;
    }
    
}
