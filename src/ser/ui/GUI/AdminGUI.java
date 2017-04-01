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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import ser.admin.IntAdmin;
import ser.ui.IntUI;

/**
 *
 * @author Parth Doshi
 */

public class AdminGUI extends JFrame implements IntUI,ActionListener{
    JFrame f1;
    JButton[] jb1=new JButton[6];
    JButton[] jb2;
    JLabel[] jl;
    final IntAdmin ia;
    public AdminGUI(IntAdmin ia){
        this.ia=ia;
        //List<String> abc = ia.getAllUname(LocalDate.MIN);
        JPanel p1=new JPanel(new FlowLayout());
        jb1[1]=new JButton("A");
        jb1[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //refresh page
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
        f1 = new JFrame("Software-Monitor");
        f1.setLayout(new GridLayout(3, 2));
	p1.add(jb1[1]);
        p1.add(jb1[5]);
        p1.add(jb1[6]);
        p1.add(jb1[2]);
        p1.add(jb1[3]);
        p1.add(jb1[4]);
        
        jb1[2].setEnabled(true);
        jb1[3].setEnabled(false);
        jb1[4].setEnabled(false);

        JPanel p2=new JPanel(new GridLayout(300, 2));        
        f1.setVisible(true);
	f1.setSize(500, 400);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
        //System.out.println("ABCD");
        String mes =(JOptionPane.showInputDialog(msg));
        return mes;
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}