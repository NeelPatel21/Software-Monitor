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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

/**
 * this class provide a facility to get all software details.
 * @author Parth Doshi
 */

public class Scheduler{
    void schedule(LocalTime lt) {
        try{
            String path=System.getenv("appdata");
            Path fullpath = Paths.get(path);
            Files.createDirectories(fullpath);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}