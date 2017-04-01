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

class ListPage extends JFrame implements ActionListener{
    final IntAdmin ia;
    JLabel[] jl;
    JPanel p1,p2;
    public ListPage(IntAdmin ia,String uname,LocalDate dt){
        p1=new JPanel(new FlowLayout());
        List<IntDataBean> abc = ia.getUserDetail(uname, dt);
        p2=new JPanel(new GridLayout(abc.size(), 3));
        this.ia=ia;
        jl[1]=new JLabel("Username : "+uname);
        //jl[2]=new JLabel("IP Address : "+ia.getIp);
        //jl[3]=new JLabel("Mac Address : "+ia.getMac);
        p1.add(jl[1]);
        p1.add(jl[2]);
        p1.add(jl[3]);
        jl[4]=new JLabel("Software Name");
        jl[5]=new JLabel("Version");
        jl[6]=new JLabel("Installed Date");
        p2.add(jl[4]);
        p2.add(jl[5]);
        p2.add(jl[6]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
