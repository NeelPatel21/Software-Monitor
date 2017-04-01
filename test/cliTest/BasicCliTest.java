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
package cliTest;

import java.nio.file.Paths;
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
public class BasicCliTest {
    //private BasicCliTest(IntAdmin ia){}
    public static void main(String arg[]){
        IntDataBase db=new LogDataBase(Paths.get("temp","log").toAbsolutePath());
        IntAdmin ia=new Admin(db);
        IntUI cli=new BasicCli(ia);
        cli.start();
    }
}
