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
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Neel Patel
 */
public interface IntMainSer extends Remote{
    //boolean aya()throws RemoteException;
    //String tokan()throws RemoteException;
    
    /**
     * implementation of this method should return unique key.
     * this method will be used to get key by remote client.
     * this method must be thread safe.
     * @param pcName user name
     * @return key as String.
     * @throws RemoteException 
     */
    String getKey(String pcName)throws RemoteException;
    
    /**
     * implementation of this method should log the details contained
       in the Object {@code db}.
     * @param db object of IntDataBean.
     * @param key unique key assigned to the user.
     * @return true if the logging successfully, false otherwise.
     * @throws RemoteException 
     */
    boolean log(IntDataBean db,String key)throws RemoteException;
    //boolean errLog(IntLogBean)throws RemoteException;
}
