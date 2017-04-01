/*
* Copyright 2017 Program Tester Team
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at

*     http://www.apache.org/licenses/LICENSE-2.0

* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* package lib.adminModule;
*/
package ser.ui.GUI;

import com.dataBean.IntDataBean;
import com.dataBean.IntDataTuple;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import ser.admin.IntAdmin;

/**
 *
 * @author Parth Doshi
 */

class ListPage extends JFrame {
    final IntAdmin ia;
    JLabel[] jl;
    JLabel[] sname;
    JLabel[] ver;
    JLabel[] idate;
    
    JPanel p1,p2;
    
    public ListPage(IntAdmin ia,String uname,LocalDate dt){
        this.ia=ia;
        p1=new JPanel(new GridLayout(3, 2));
        List<IntDataBean> abc = ia.getUserDetail(uname, dt);
        jl[1]=new JLabel("Username : "+uname);
        jl[2]=new JLabel("IP Address : "+abc.get(0).getIP());
        jl[3]=new JLabel("Mac Address : "+abc.get(0).getMac());
        p1.add(jl[1]);
        p1.add(jl[2]);
        p1.add(jl[3]);
        //Panel 1 ends
        jl[4]=new JLabel("Software Name");
        jl[5]=new JLabel("Version");
        jl[6]=new JLabel("Installed Date");
        p2=new JPanel(new GridLayout(abc.size(), 3));
        p2.add(jl[4]);
        p2.add(jl[5]);
        p2.add(jl[6]);
        int i = 1;
        for(IntDataBean p:abc){
            for(IntDataTuple idt:p.getSoftDetail()){
                sname[i]=new JLabel(idt.getSoftName());
                ver[i]=new JLabel(idt.getVersion());
                idate[i]=new JLabel(idt.getDate().toString());
                p2.add(sname[i]);
                p2.add(ver[i]);
                p2.add(idate[i]);
                i++;
            }
        }
        //Panel 2 ends
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
