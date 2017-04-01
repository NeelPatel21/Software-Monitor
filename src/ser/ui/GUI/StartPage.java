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

package ser.ui.GUI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import ser.admin.IntAdmin;
import ser.ui.IntUI;
import java.util.List;

/**
 *
 * @author Parth Doshi
 */

public class StartPage extends JFrame implements IntUI{
    JLabel jl=new JLabel("Enter Date : ");
    JButton[] jb=new JButton[3];
    JTextField jtf;
    LocalDate date;
    IntAdmin ia=null;

    public StartPage(IntAdmin ia) {
        this.ia=ia;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public LocalDate getDate(IntAdmin ia){
        this.ia=ia;
        add(jl);
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setLayout(new GridLayout(10, 1));
        jtf=new JTextField(0);
        jtf.setText(LocalDate.now().toString());
        String sdate=jtf.getText();
        
        add(jtf);
        jb[0]=new JButton("Update");
        jb[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
            date = LocalDate.parse(sdate);
            } catch (Exception ex) {
            showMessage("Invalid Date Format");
        }
            }
        });
        jb[1]=new JButton("Start Server");
        jb[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.startSer();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        jb[2]=new JButton("Stop Server");
        jb[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.stopSer();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        add(jl);
        add(jtf);
        add(jb[0]);
        add(jb[1]);
        add(jb[2]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    return date;
}
    /*
    LocalDate date;
    final IntAdmin ia;
        //JFrame f1 = new JFrame("Software-Monitor");
        setLayout(new GridLayout(3, 2));
        this.ia=ia;
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        jtf=new JTextField();
        jtf.setSize(10, 20);
        List<String> abc = ia.getAllUname(date);
        jl = new JLabel[abc.size()];
        jb2 = new JButton[abc.size()];
        int i = 0;
    //    JPanel p1=new JPanel(new GridLayout(2, 4));
      //  JPanel p2=new JPanel(new GridLayout(abc.size(), 2));
        
        jb1[1]=new JButton("Update");
        jb1[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //p2.repaint();
                p2.revalidate();
            }
        });
        jb1[2]=new JButton("Connect database");
        jb1[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.condb();
            }
        });
        jb1[3]=new JButton("Close Database Connection");
        jb1[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.remdb();
            }
        });
        jb1[4]=new JButton("Open");
        jb1[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        jb1[5]=new JButton("Start Server");
        jb1[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.startSer();
            }
        });
        jb1[6]=new JButton("Stop Server");
        jb1[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ia.stopSer();
            }
        });
        add(p1);
        add(p2);
	p1.add(jb1[1]);
        p1.add(jb1[5]);
        p1.add(jb1[6]);
        p1.add(jb1[2]);
        p1.add(jb1[3]);
        p1.add(jb1[4]);
        
        jb1[2].setEnabled(true);
        jb1[3].setEnabled(false);
        jb1[4].setEnabled(false);
        setVisible(false);
        setSize(xSize,ySize);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void paint(Graphics g) {
        
    }
    
    @Override
    public void dbStart() {
        jb1[3].setEnabled(true);
        jb1[4].setEnabled(true);
    }

    @Override
    public void dbStop() {
        jb1[3].setEnabled(false);
        jb1[4].setEnabled(false);
    }
*/
    @Override
    public void showMessage(String msg) {
        /**
         * This method opens a dialog box showing a message
         */
        JOptionPane.showMessageDialog(null, msg, "Message Box", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String showPrompt(String msg) {
        /**
         * This method is called when a user input is required
         */
        String mes =(JOptionPane.showInputDialog(msg));
        return mes;
    }

    @Override
    public void start() {
    }

    @Override
    public void close() throws IOException {
        
    }
 }