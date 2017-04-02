/**
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
package serTest;

import client.flow.ClientFlow;
import java.util.Scanner;

/**
 *
 * @author Neel Patel
 */
public class ClientTest {
    static Scanner sc=new Scanner(System.in);
    public static void main(String arg[]){
        System.out.println("enter url :- ");
        String s="";
        for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
        ClientFlow cf=new ClientFlow(s.trim());
        cf.start();
    }
}
