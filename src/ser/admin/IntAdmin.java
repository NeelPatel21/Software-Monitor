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
package ser.admin;

import com.dataBean.IntDataBean;
import java.io.Closeable;
import java.time.LocalDate;
import java.util.List;
import ser.ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public interface IntAdmin extends Closeable{
    /**
     * implementation of this method should register the object
       {@code ui}.
     * @param ui Object of type IntUI.
     * @return return true if successfully registered.
     */
    boolean registerUI(IntUI ui);
    
    /**
     * start the RMI server.
     */
    void startSer();
    
    /**
     * stop the RMI server.
     */
    void stopSer();
    
    /**
     * @deprecated 
     */
    void condb();
    
    /**
     * @deprecated 
     */
    void remdb();
    
    /**
     * implementation of this method should return the List of uses
       who have logged on the specified date.
     * @param dt
     * @return 
     */
    List<String> getAllUname(LocalDate dt);
    
    /**
     * implementation of this method should return all the details
       of the user on specified date. 
     * @param uName user name
     * @param dt object of type LocalDate.
     * @return 
     */
    List<IntDataBean> getUserDetail(String uName,LocalDate dt);
}
