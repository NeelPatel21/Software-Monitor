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
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import ser.admin.IntAdmin;
import ser.ui.IntUI;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Parth Doshi
 */

public class AdminGUI extends JFrame implements ActionListener{
    //JFrame f1;
    JButton[] jb1=new JButton[6];
    JButton[] jb2;
    JLabel[] jl;
    JTextField jtf;
    LocalDate date;
    final IntAdmin ia;
    public AdminGUI(IntAdmin ia){
        new JFrame("Software-Monitor");
        setLayout(new GridLayout(3, 2));
        this.ia=ia;
        jtf=new JTextField();
        String sdate=jtf.getText();
        try {
            date = LocalDate.of(new Integer(sdate.substring(0, 4)).intValue(), new Integer(sdate.substring(5, 7)).intValue(), new Integer(sdate.substring(8)).intValue());
        } catch (Exception ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> abc = ia.getAllUname(date);
        jl = new JLabel[abc.size()];
        jb2 = new JButton[abc.size()];
        int i = 0;
        JPanel p1=new JPanel(new GridLayout(2, 4));
        JPanel p2=new JPanel(new GridLayout(abc.size(), 2));
        final DatePicker dp = new DatePicker();
        
        for(String username:abc)
        {
            jl[i] = new JLabel(username);
            p2.add(jl[i]);
            jb2[i] = new JButton("Get Details");
            p2.add(jb2[i++]);
            jb2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ListPage lp=new ListPage(ia,username,date);
                }
            });
            i++;
        }
                
        jb1[1]=new JButton("Update");
        jb1[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.repaint();
                //p2.revalidate();
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
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(false);
	setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }
    
   // @Override
    public void dbStart() {
        jb1[3].setEnabled(true);
        jb1[4].setEnabled(true);
    }

   // @Override
    public void dbStop() {
        jb1[3].setEnabled(false);
        jb1[4].setEnabled(false);
    }

   // @Override
    public void showMessage(String msg) {
        /**
         * This method opens a dialog box showing a message
         */
        JOptionPane.showMessageDialog(null, msg, "Message Box", JOptionPane.INFORMATION_MESSAGE);
    }

   // @Override
    public String showPrompt(String msg) {
        /**
         * This method is called when a user input is required
         */
        String mes =(JOptionPane.showInputDialog(msg));
        return mes;
    }

   // @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   // @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
   //     AdminGUI ag=new AdminGUI();
    }
}