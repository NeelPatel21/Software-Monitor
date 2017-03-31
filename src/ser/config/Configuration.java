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
package ser.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Neel Patel
 */
public class Configuration {
    private Configuration(){}
    static private Path defLogDir=Paths.get(".").toAbsolutePath();
    static final private ReentrantReadWriteLock ldefLogDir=new ReentrantReadWriteLock();
    
    /**
      * getter method of default path.
      * it is used by constructor if the path is not provided explicitly.
      * this method is thread safe.
      * @return default path.
      */
    static public Path getDefaultLogDir(){
        try{
            ldefLogDir.readLock().lock();
            return defLogDir;
        }
        finally{
            ldefLogDir.readLock().unlock();     
        }
    }
     
    /**
      * setter method of default path.
      * this method is thread safe.<br>
      * Note:- changes made in default path is not reflected in existing objects
        of the class as they use their local variable to store path variable
        to locate the logger file.
      * @param p new default path.
      * @return true if the default path is updated with {@code p}, false if p
        is not absolute or not a directory.
      */
    static public boolean setDefaultLogDir(Path p){
        if(!p.isAbsolute()||!Files.isDirectory(p))
            return false;
        try{
            if(getDefaultLogDir().equals(p))
                return true;
            ldefLogDir.writeLock().lock();
            defLogDir=p;
            return true;
        }finally{
            ldefLogDir.writeLock().unlock();
        }
    }
     
}
