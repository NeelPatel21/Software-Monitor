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
package client.scheduler;

import client.softData.ProgramExecuter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will help in scheduling the software as per requirement
 * @author Parth Doshi
 */

public class Scheduler{
        private static final String dtf="HH:mm";
    public void schedule(LocalTime lt) {
        List<String> y = new ArrayList<>();   //C:\Users\Parth Doshi\AppData\Roaming
        Path p=Paths.get(System.getenv("appdata"),"Software Monitor","pro.exe");
        String cmd="SchTasks /Create /SC DAILY /TN “Software-Monitor” /TR "+p+" /ST "+lt.format(DateTimeFormatter.ofPattern(dtf));
        ProgramExecuter pe=new ProgramExecuter(y,cmd);
        try {
            pe.execute(5000);//.forEach(System.out::println);
        } catch (IOException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}