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
package ser.ui.cli;

import java.util.Scanner;
import ser.ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class BasicCli implements IntUI{
    private Scanner sc=new Scanner(System.in);
    
    
    @Override
    public void dbStop() {
        //coming soon.
    }

    @Override
    public void dbStart() {
        //coming soon.
    }

    @Override
    public void showMessage(String msg) {
        System.out.println("Message :- "+msg);
    }

    @Override
    public String showPrompt(String msg) {
        System.out.println("Prompt :- "+msg);
        String s="";
        for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
        return s;
    }

    @Override
    public void start() {
        for(;;){
            System.out.println("Menu :- ");
        }
    }
    
}
