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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import ser.admin.IntAdmin;

/**
 *
 * @author Parth Doshi
 */

class DataPage extends JFrame {
    final IntAdmin ia;
    JLabel[] jl;
    JLabel[] empty;
    JLabel[] sname;
    JLabel[] ver;
    JLabel[] idate;
    
    //JPanel p1,p2;
    
    public DataPage(IntAdmin ia,String uname,LocalDate dt){
        this.ia=ia;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        
        List<IntDataBean> abc = ia.getUserDetail(uname, dt);
        jl[1]=new JLabel("Username : "+uname);
        jl[2]=new JLabel("IP Address : "+abc.get(0).getIP());
        jl[3]=new JLabel("Mac Address : "+abc.get(0).getMac());
        add(jl[0]);
        add(empty[0]);
        add(empty[1]);
        add(jl[1]);
        add(empty[2]);
        add(empty[3]);
        add(jl[2]);
        //Panel 1 ends
        jl[3]=new JLabel("Software Name");
        jl[4]=new JLabel("Version");
        jl[5]=new JLabel("Installed Date");
        //new JPanel(new GridLayout(abc.size(), 3));
        add(jl[3]);
        add(jl[4]);
        add(jl[4]);
        int i = 0;
        for(IntDataBean p:abc){
            for(IntDataTuple idt:p.getSoftDetail()){
                sname[i]=new JLabel(idt.getSoftName());
                ver[i]=new JLabel(idt.getVersion());
                idate[i]=new JLabel(idt.getDate().toString());
                add(sname[i]);
                add(ver[i]);
                add(idate[i]);
                i++;
            }
        }
        setLayout(new GridLayout(abc.size(), 3));
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
