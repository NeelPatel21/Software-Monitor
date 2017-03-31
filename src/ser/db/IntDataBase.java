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
    List<IntDataBean> getAllUserDetail(LocalDate date);
    List<IntDataBean> getAllUserDetail(LocalDate sd,LocalDate ed);
    List<IntDataBean> getUserDetail(String user,LocalDate sd,LocalDate ed);
    List<IntDataBean> getSoftDetail(String soft,LocalDate sd,LocalDate ed);
    int removeUser(String user);
    int removeSoft(String soft);
    int removeLogs(LocalDate ld);
    int removeLogsUpto(LocalDate ld);
}
