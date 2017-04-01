/*
 * Copyright 2017 Sony.
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

package client.filter;

import com.dataBean.DataBeans;
import com.dataBean.DataTuple;
import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Sony
 */
public class Filter {
   
     List<String> ls = null;
     Set<String> se = null;
     List<DataTuple> dt = new ArrayList<DataTuple>();
   public Filter(List<String> ls)
    {
        this.ls = ls;
       //se.addAll(ls);
    }
    
   
   public synchronized boolean refresh(List<String> ls)
   {
       return se.addAll(ls);
   }
   
    public synchronized List<DataTuple> filterdata()
    {
       /* if(ls.size() == 0)
            return null;
        
        se = new TreeSet<String>();
        if(ls.size() == 0)
            return null;
        DataTuple dtt = null;*/
     String name="";
     String ver="";
     LocalDate date=null;
    
        List<String> temp = new ArrayList<String>();
        
        for(String s:ls)
        {
            
            if(s.trim().contains("[HKEY_LOCAL_MACHINE") || s.equals(ls.get(ls.size()-1)))
            {
                   if(name!="" || ver!="" || date!=null)
                        dt.add((DataTuple) DataBeans.getDataTuple(name, ver, date));
                    name = "";
                    ver = "";
                    date = null;
            }
            
            
            if((s.trim().contains("\"DisplayName\"=")))
            {
                //System.out.println(s);
                String[] g = s.trim().split("\"DisplayName\"=");
                //System.out.println(g[1].substring(1, g[1].length()-1));
                name = (g[1].substring(1, g[1].length()-1).trim());
            } else {
            }
            
            if((s.trim().contains("\"DisplayVersion\"=")))
            {
                //System.out.println(s);
                String[] g = s.trim().split("\"DisplayVersion\"=");
                //System.out.println(g[1].substring(1, g[1].length()-1));
                ver = (g[1].substring(1, g[1].length()-1).trim());
            } else {
            }
            
            if((s.trim().contains("\"InstallDate\"=")))
            {
                //System.out.println(s);
                String[] g = s.trim().split("\"InstallDate\"=");
                String tr = "";//System.out.println(g[1].substring(1, g[1].length()-1));
                
                 tr = (g[1].substring(1, g[1].length()-1).trim());
                 
                date = LocalDate.of(new Integer(tr.substring(0, 4)).intValue(), new Integer(tr.substring(4, 6)).intValue(), new Integer(tr.substring(6)).intValue());
            } else {
            }
        }
        //return se;
         return dt;
    }
    /*public static void main(String args[]) throws IOException
    {
        Path p;
        p = Paths.get("C:","Users","Sony","Desktop","out1.txt");
        
        List<String> ls = null;
         ls = (Files.readAllLines(p,Charset.forName("UTF-16")));
         for (DataTuple dt: new Filter(ls).filterdata())
         {
             System.out.println(dt.getSoftName());
             System.out.println(dt.getVersion());
             System.out.println(dt.getDate());
         }
        //for(String s :ls)
          //  System.out.println(s);
        
                
    }*/
    
}

