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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.JLabel;
import ser.admin.IntAdmin;

class DataPage extends JFrame {
    List<IntDataBean> abc = null;
    IntAdmin ia = null;
    boolean flag = true;
    JLabel[] jb = new JLabel[3];
    
    JLabel[] js = new JLabel[10];
    JLabel[] jv = new JLabel[10];
    JLabel jss = new JLabel();
    JLabel jvv=new JLabel();
    JButton jnext = null;
    JButton jprev = null;
    int i= 0;
    int page_number = 1;
    String uname = "";
    List<IntDataTuple> idt = null;
    public DataPage(IntAdmin ia,String uname,LocalDate dt){
        js[0]=new JLabel();
        this.uname = uname;
        setLayout(new FlowLayout());
        //setLayout(new GridLayout(11, 3));
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setVisible(true);
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
     
        abc= ia.getUserDetail(uname, dt);
        idt = abc.get(0).getSoftDetail();
        
    }
    public void paint(Graphics g)
    {
        //g.drawString("PArth",50, 50);
     if(flag)
     {
         //System.out.println(abc.size());
         if(abc.size() == 0)
             jb[0] =  new JLabel("None");
         else{
         flag = false;
         jb[0] = new JLabel("Username: "+abc.get(0).getName());
         
         jb[1] = new JLabel("Mac: "+abc.get(0).getMac());
         jb[2] = new JLabel("IP: "+abc.get(0).getIP());
         
         add(jb[0]);
         add(jb[1]);
         add(jb[2]);
         }
         
     }else
     {
         jss = new JLabel("Software - name: ");
         jvv = new JLabel("Version number: ");
         jss.setPreferredSize(new Dimension(50,50));
         jvv.setPreferredSize(new Dimension(50,50));

         add(jss);
         add(jvv);
         for(int j=0;j<10;j++)
         {
             //System.out.println(j); 
             js[j] = new JLabel(idt.get(i).getSoftName());
             System.out.println(idt.get(i).getSoftName());
             jv[j] = new JLabel(idt.get(i).getVersion());
             add(js[j]);
             add(jv[j]);
             i++;
         }
     }
     jnext = new JButton("next");
     jprev = new JButton("prev");
     add(jnext);
     add(jprev);
     jnext.addActionListener(new ActionListener()
     {

         @Override
         public void actionPerformed(ActionEvent e) {
               if(i<=js.length)
                    repaint();
         }
    });
     jprev.addActionListener(new ActionListener()
     {

         @Override
         public void actionPerformed(ActionEvent e) {
             
             if(i>10){
                i-=10;
             repaint(); }
         }
    });
     
        
    }
}
/*
import com.dataBean.IntDataBean;
import com.dataBean.IntDataTuple;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import ser.admin.IntAdmin;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;


/**
 *
 * @author Parth Doshi
 */

/**class DataPage extends JFrame {
    JLabel[] empty;
    int i=0;
    JLabel[] sname = new JLabel[20];
        JLabel[] ver =  new JLabel[20];
        
    public DataPage(IntAdmin ia,String uname,LocalDate dt){
        JPanel p1 = new JPanel(new GridLayout(3, 2));
        JPanel p2 = new JPanel(new GridLayout(500,3));
        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL);
        JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL);
        hbar.setUnitIncrement(2);
        hbar.setBlockIncrement(1);
        hbar.addAdjustmentListener(new MyAdjustmentListener());
        vbar.addAdjustmentListener(new MyAdjustmentListener());

        add(hbar, BorderLayout.SOUTH);
        add(vbar, BorderLayout.EAST);
        
        this.ia=ia;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        jl = new JLabel[6];
        empty = new JLabel[6];
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
        p1.add(jl[0]);
        add(empty[0]);
        add(empty[1]);
        p1.add(jl[1]);
        add(empty[2]);
        add(empty[3]);
        p1.add(jl[2]);
        add(empty[4]);
        add(empty[5]);
        p1.setPreferredSize(new Dimension(xSize, 50));
        //js = new JScrollPane(p1);
        this.add(p1);
//Top ends
        jl[3]=new JLabel("Software Name");
        jl[3].setVerticalAlignment(SwingConstants.CENTER);
        jl[4]=new JLabel("Version");
        jl[4].setVerticalAlignment(SwingConstants.CENTER);
        //jl[5]=new JLabel("Installed Date");
        //jl[5].setVerticalAlignment(SwingConstants.CENTER);
        p2.add(jl[3]);
        p2.add(jl[4]);
        //add(jl[5]);
        int i = 0;
        System.out.println(abc.size());
        sname=new JLabel[abc.get(0).getSoftDetail().size()];
        ver=new JLabel[abc.get(0).getSoftDetail().size()];
        //idate=new JLabel[abc.get(0).getSoftDetail().size()];
        setLayout(new GridLayout(abc.get(0).getSoftDetail().size()+5, 2));
        
        
        p2.setPreferredSize(new Dimension(xSize, 300));
        this.add(p2);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    class MyAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
        i=e.getValue();
        repaint();
    }
  }
    public void paint(Graphics G){
        
        int j = 0;
        
        if(abc.size()-i < 20)
        List<IntDataBean> lid = abc.    
        for(IntDataBean p:){
            for(IntDataTuple idt:p.getSoftDetail()){
                sname[i]=new JLabel(idt.getSoftName());
                sname[i].setVerticalAlignment(SwingConstants.CENTER);
                p2.add(sname[i]);
                ver[i]=new JLabel(idt.getVersion());
                ver[i].setVerticalAlignment(SwingConstants.CENTER);
                p2.add(ver[i]);
                //idate[i]=new JLabel(idt.getDate().toString());
                //idate[i].setVerticalAlignment(SwingConstants.CENTER);
                //add(idate[i]);
                i++;
            }
        }
        
    
    }
}*/
