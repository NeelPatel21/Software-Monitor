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
package serTest;

import com.net.remSer.IntMainSer;
import com.net.remSer.MainSer;
import com.net.remSer.MainSerHandle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import static ser.UrlTools.registerObj;
import static ser.config.Configuration.setDefaultLogDir;
import ser.keyGen.KeyGen;
import ser.logger.MyLog;

/**
 *
 * @author Neel Patel
 */
public class MainSerTest {
    static Scanner sc=new Scanner(System.in);
    public static void main(String arg[]) throws RemoteException, NotBoundException, MalformedURLException, IOException{
        Path pt=Paths.get("temp","log").toAbsolutePath();
        Files.createDirectories(pt);
        setDefaultLogDir(pt);
        System.out.println("path - "+pt);
        //MyLog.SetDir(pt);
        MyLog mg=new MyLog();
        MainSerHandle mh=new MainSerHandle();
        mh.setKeysp(KeyGen::getKey);
        mh.setLoger(mg::log);
        IntMainSer ms=new MainSer(mh);
        System.out.println("enter 1 to start server..");
        if(sc.nextInt()!=1)
            return;
        try{
            Registry r=LocateRegistry.createRegistry(8686);
        }catch(Exception e){
            System.out.println("registry fail");
        }
        String uri=registerObj(ms,8686,"main");
        System.out.println("uri :- "+uri);
        System.out.println("enter 0 to stop server..");
        for(;sc.nextInt()!=0;);
        Naming.unbind("main");
        return;
        //System.exit(0);
    }
    
}
