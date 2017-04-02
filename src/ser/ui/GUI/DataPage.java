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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
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
    
    
    public DataPage(IntAdmin ia,String uname,LocalDate dt){
        //System.out.println("arrived");
        this.ia=ia;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
	jl = new JLabel[6];
        empty = new JLabel[6];
        List<IntDataBean> abc = ia.getUserDetail(uname, dt);
        jl[0]=new JLabel("Username : "+uname);
        jl[0].setVerticalAlignment(SwingConstants.CENTER);
        jl[1]=new JLabel("IP Address : "+(abc.size()>0?(abc.get(0).getIP()):""));
        jl[1].setVerticalAlignment(SwingConstants.CENTER);
        jl[2]=new JLabel("Mac Address : "+(abc.size()>0?(abc.get(0).getMac()):""));
        jl[2].setVerticalAlignment(SwingConstants.CENTER);
        empty[0]=new JLabel();
        empty[1]=new JLabel();
        empty[2]=new JLabel();
        empty[3]=new JLabel();
        empty[4]=new JLabel();
        empty[5]=new JLabel();
        add(jl[0]);
        add(empty[0]);
        add(empty[1]);
        add(jl[1]);
        add(empty[2]);
        add(empty[3]);
        add(jl[2]);
        add(empty[4]);
        add(empty[5]);
        //Top ends
        jl[3]=new JLabel("Software Name");
        jl[3].setVerticalAlignment(SwingConstants.CENTER);
        jl[4]=new JLabel("Version");
        jl[4].setVerticalAlignment(SwingConstants.CENTER);
        jl[5]=new JLabel("Installed Date");
        jl[5].setVerticalAlignment(SwingConstants.CENTER);
        add(jl[3]);
        add(jl[4]);
        add(jl[5]);
        int i = 0;
        System.out.println(abc.size());
        sname=new JLabel[abc.get(0).getSoftDetail().size()];
        ver=new JLabel[abc.get(0).getSoftDetail().size()];
        idate=new JLabel[abc.get(0).getSoftDetail().size()];
        setLayout(new GridLayout(abc.get(0).getSoftDetail().size()+5, 3));
        
        for(IntDataBean p:abc){
            for(IntDataTuple idt:p.getSoftDetail()){
                sname[i]=new JLabel(idt.getSoftName());
                sname[i].setVerticalAlignment(SwingConstants.CENTER);
                add(sname[i]);
                ver[i]=new JLabel(idt.getVersion());
                ver[i].setVerticalAlignment(SwingConstants.CENTER);
                add(ver[i]);
                idate[i]=new JLabel(idt.getDate().toString());
                idate[i].setVerticalAlignment(SwingConstants.CENTER);
                add(idate[i]);
                i++;
            }
        }
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
