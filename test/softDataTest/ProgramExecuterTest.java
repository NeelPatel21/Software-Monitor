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
package softDataTest;

import client.softData.ProgramExecuter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.Y;

/**
 *
 * @author Rushabh Modi
 */
public class ProgramExecuterTest {
    public static void main() throws IOException{
        List<String> y = new ArrayList<>();
        y.add("5");
        y.add("4635");
        y.add("654");
        y.add("3623");
        y.add("578");
        y.add("135");
        String cmd="java C:\\Users\\Rushabh\\Documents\\NetBeansProjects\\Sorting\\build\\classes\\Sorting";
        ProgramExecuter pe=new ProgramExecuter(y,cmd);
        pe.execute(5000).forEach(System.out::println);
    }
}
