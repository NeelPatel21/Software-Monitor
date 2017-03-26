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

import java.util.Arrays;

/**
 *
 * @author Neel Patel
 */
public class SoftData {
    private static final String DEF_KEYS[]={
        "HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall",
        "HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Uninstall"
    };
    
    private final String ar[];
    private ProcessBuilder pb;
    public SoftData(){
        ar=DEF_KEYS;
    }
    
    public SoftData(String... reg){
        this.ar=Arrays.copyOf(reg, reg.length);
    }
    
    private void run(){
        
    }
}
