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
package softwareMonitor;

import client.flow.ClientFlow;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import ser.admin.Admin;
import ser.admin.IntAdmin;
import ser.db.IntDataBase;
import ser.db.logData.LogDataBase;
import ser.ui.IntUI;
import ser.ui.cli.BasicCli;

/**
 *
 * @author Neel Patel
 */
public class SoftwareMonitor {
    public static void main(String... arg){
        //AdminLogCli();
        Client();
    }
    
    public static void AdminLogCli(){
        IntDataBase db=new LogDataBase(Paths.get("temp","log").toAbsolutePath());
        IntAdmin ia=new Admin(db);
        IntUI cli=new BasicCli(ia);
        cli.start();
    }
    
    public static void Client(){
        try {
            Path p=Paths.get(System.getenv("appdata"),"Software Monitor","url.txt");
            String s=Files.readAllLines(p).stream()
                      .filter(i->!i.trim().isEmpty())
                      .findFirst().orElse("");
            ClientFlow cf=new ClientFlow(s.trim());
            cf.start();
        } catch(Exception ex) {
        }
    }
}
