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
import java.time.LocalDate;
import java.util.List;
import ser.db.IntDataBase;

/**
 *
 * @author Neel Patel
 */
public class Admin implements IntAdmin{
    private final IntDataBase db;
    
    public Admin(IntDataBase db){
        this.db=db;
    }
    
    @Override
    public void startSer() {
        //coming soon
    }

    @Override
    public void stopSer() {
        //coming soon
    }

    @Override
    public List<String> getAllUname(LocalDate dt) {
        db.getAllUserDetail(LocalDate)
    }

    @Override
    public List<IntDataBean> getUserDetail(String uName, LocalDate dt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
