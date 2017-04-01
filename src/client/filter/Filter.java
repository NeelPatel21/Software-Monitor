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

import com.dataBean.DataTuple;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
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
   
    public synchronized Set<String> filterdata()
    {
        if(ls.size() == 0)
            return null;
        
        se = new TreeSet<String>();
        if(ls.size() == 0)
            return null;
        DataTuple dtt = null;
        for(String s:ls)
        {
            
            if(s.trim().contains("[HKEY_LOCAL_MACHINE"))
            {
                //dtt = Data
            }
            
            
            if((s.trim().contains("\"DisplayName\"=")))
            {
                //System.out.println(s);
                String[] g = s.trim().split("\"DisplayName\"=");
                //System.out.println(g[1].substring(1, g[1].length()-1));
                se.add(g[1].substring(1, g[1].length()-1).trim());
            } else {
            }
        }
        return se;
    }
    
}

