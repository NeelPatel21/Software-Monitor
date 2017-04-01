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
package ser.db;

import com.dataBean.IntDataBean;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Neel Patel
 */
public interface IntDataBase {
    
    /**
     * implementation of this method should return should return all
       the software details on the date referred by {@code date}.
     * this method should return list of Objects of IntDataBean. each 
       element of the list refers to a details of particular user.
     * @param date Object of LocalDate.
     * @return List of Object of IntDataBean.
     */
    List<IntDataBean> getAllUserDetail(LocalDate date);
    
    /**
     * implementation of this method should return should return all
       the software details of the date between {@code sd} & {@code ed}
       of all the user.
     * here the {@code ed} is exclusive. i.e. details of the date {@code ed}
       will not returned.
     * the LocalDate Object {@code sd} must be less then {@code ed}.
     * if the {@code sd} is greater then or equal to ed then this method
       should return empty list.
     * this method should return list of Objects of IntDataBean. each 
       element of the list refers to a log of particular user.
     * @param sd Object of starting LocalDate.
     * @param ed Object of ending LocalDate(exclusive).
     * @return List of Object of IntDataBean.
     */
    List<IntDataBean> getAllUserDetail(LocalDate sd,LocalDate ed);
    
    /**
     * implementation of this method should return should return all
       the software details of the date between {@code sd} & {@code ed}
       of specified user.
     * here the {@code ed} is exclusive. i.e. details of the date {@code ed}
       will not returned.
     * the LocalDate Object {@code sd} must be less then {@code ed}.
     * if the {@code sd} is greater then or equal to ed or the specified user
       is not exist then this method should return empty list.
     * this method should return list of Objects of IntDataBean. each 
       element of the list refers to a details of particular user.
     * @param user user name
     * @param sd Object of starting LocalDate.
     * @param ed Object of ending LocalDate(exclusive).
     * @return List of Object of IntDataBean.
     */
    List<IntDataBean> getUserDetail(String user,LocalDate sd,LocalDate ed);
    
    /**
     * implementation of this method should return should return
       the software details of the date between {@code sd} & {@code ed}
       of all user.
     * here the {@code ed} is exclusive. i.e. details of the date {@code ed}
       will not returned.
     * the LocalDate Object {@code sd} must be less then {@code ed}.
     * if the {@code sd} is greater then or equal to ed or the specified software
       is not exist then this method should return empty list.
     * this method should return list of Objects of IntDataBean. each 
       element of the list refers to a details of particular user.
     * @param soft software name
     * @param sd Object of starting LocalDate.
     * @param ed Object of ending LocalDate(exclusive).
     * @return List of Object of IntDataBean.
     */
    List<IntDataBean> getSoftDetail(String soft,LocalDate sd,LocalDate ed);
    
    /**
     * implementation of this method should remove all the record of
       the specified user.
     * @param user username.
     * @return number of records removed.
     */
    int removeUser(String user);
    
    /**
     * implementation of this method should remove all the record of
       the specified software.
     * @param soft username.
     * @return number of records removed.
     */
    int removeSoft(String soft);
    
    /**
     * implementation of this method should remove all the record of
       particular date.
     * @param ld object of LocalDate.
     * @return number of records removed.
     */
    int removeLogs(LocalDate ld);
    
    /** 
     * implementation of this method should remove all the record upto
       particular date.
     * @param ld object of LocalDate.
     * @return number of records removed.
     */
    int removeLogsUpto(LocalDate ld);
}
