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

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ser.admin.IntAdmin;
import ser.ui.IntUI;

/**
 *
 * @author Neel Patel
 */
public class BasicCli implements IntUI{
    private Scanner sc=new Scanner(System.in);
    private final IntAdmin ia;
    
    public BasicCli(IntAdmin ad){
        ia=ad;
        ia.registerUI(this);
    }
    
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
            System.out.println("Menu...\n"
                            +"1.Start Server\n"
                            +"2.Stop Server\n"
                            +"3.Show all User\n"
                            +"4.Show User Detail\n"
                            +"0.exit\n"
                        );
            switch(sc.nextInt()){
                case 1:ia.startSer();
                        break;
                case 2:ia.stopSer();
                        break;
                case 3:showAllUser();
                        break;
                case 4:showUserDetail();
                        break;
                case 0:{
                            try {
                                ia.close();
                            } catch(IOException ex) {
                            }
                        }
                        return;
                default:System.out.println("Invalid Choice!!!\n");
                        break;
            }
        }
    }
    
    private void showAllUser(){
        System.out.println("enter date in yyyy-mm-dd:-");
        String s="";
        for(s=sc.nextLine();s.trim().isEmpty();s=sc.nextLine());
        try{
            LocalDate d=LocalDate.parse(s.trim(),DateTimeFormatter.ISO_LOCAL_DATE);
            ia.getAllUname(d).forEach(System.out::println);
        }catch(Exception ex){
            System.out.println("error4 :- "+ex);
        }
    }
    
    private void showUserDetail(){
        System.out.println("enter user name:-");
        String name="";
        for(name=sc.nextLine();name.trim().isEmpty();name=sc.nextLine());
        System.out.println("enter date in yyyy-mm-dd:-");
        String date="";
        for(date=sc.nextLine();date.trim().isEmpty();date=sc.nextLine());
        try{
            LocalDate d=LocalDate.parse(date.trim(),DateTimeFormatter.ISO_LOCAL_DATE);
            ia.getUserDetail(name, d).forEach(i->{
                System.out.println("ip :- "+i.getIP());
                System.out.println("mac :- "+i.getMac());
                System.out.println("Date\t\tinstall\t\tversion\tSoftware");
                i.getSoftDetail().forEach(x->{
                    System.out.println(i.getTime().toLocalDate()+"\t"
                            +(x.getDate()==null?"not available":x.getDate())+"\t"
                            +(x.getVersion().trim().isEmpty()?"none":x.getVersion())+"\t"
                            +x.getSoftName()+"\t");
                });
            });
        }catch(Exception ex){
            System.out.println("error3 :- "+ex);
        }
    }

    @Override
    public void close() throws IOException {
    }
    
}
