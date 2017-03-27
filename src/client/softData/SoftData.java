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
package client.softData;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Path;

/**
 * this class provide a facility to get all software details.
 * @author Neel Patel
 */
public class SoftData {
    private static final String DEF_KEYS[]={
        "HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall",
        "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall"
    };
    
    private final String ar[];
    private List<String> out=new ArrayList<>();//list of outputs of all programs.
    private boolean isExecuted=false;//false if not executed yet,true if already executed.
    //private Thread t=null;
    
    /**
     * this is default constructor.
     * this constructor use default keys to read the software details.
     */
    public SoftData(){
        ar=DEF_KEYS;
    }
    
    /**
     * @param reg registry keys, which will used to get the software details.
     */
    public SoftData(String... reg){
        this.ar=Arrays.copyOf(reg, reg.length);
    }
    
    /**
     * this method loops on all the keys to get the Software details.
     * this method will append the output of key to the List {@code out}.
     * this method uses the temporary folder to get the details.
     */
    private void run(){
        isExecuted=true;
        //System.out.println("run start");
        for(String s:ar){
            try {
                //System.out.println("run :- in loop");
                Path p=Paths.get(System.getenv("temp"));
                Files.createDirectories(p);
                p=p.resolve("Software_Det.txt");
                Files.deleteIfExists(p);
                String cmd="reg.exe "+"EXPORT "+s+" "+
                          p.toString()+" /y";
                ProgramExecuter pe=new ProgramExecuter(new ArrayList<>(),cmd);
                //System.out.println("check 1");
                pe.execute(5000);//.forEach(System.out::println);
                Thread.sleep(1000);
                List<String> o= Files.readAllLines(p,Charset.forName("UTF-16"));
                out.addAll(o);
                Files.deleteIfExists(p);
            } catch(Exception ex) {
                System.out.println("error :- "+ex);
                ex.printStackTrace();
            }
        }
    }

    /**
     * this method start the process of getting software details.
     * if this method already executed then old result will be returned.
     * this method return output unmodifiable List of String.
     * @return output as List of String 
     */
    public synchronized List<String> start(){
        if(isExecuted)
            return null;
        run();
        return out;
    }
}
