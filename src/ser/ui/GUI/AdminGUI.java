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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Parth Doshi
 */

public class AdminGUI extends JFrame implements ActionListener{
    JFrame f1,f2;
    JButton jb;
    JLabel jl;
    JPanel panel;
    JTextField textbox;
    static JTable table;
    JButton b1;
    String[] columnNames = {"",""};

    public AdminGUI(){
        f1 = new JFrame("Swing and JavaFX");
        f1.setLayout(new GridLayout(3, 3));
	jb=new JButton();
        jl=new JLabel();
        b1 = new JButton("Search");
	b1.setBounds(120,130,150,20);
	
        b1.addActionListener(this);
        
        f1.add(textbox);
	f1.add(jl);
	f1.add(b1);
        f1.setVisible(true);
	f1.setSize(500, 400);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        b1 = (JButton)ae.getSource();
        System.out.println("Showing Table Data.......");
	//showTableData();			
    }
    public void showTableData()
	{
	    f2 = new JFrame("Database Search Result");
            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f2.setLayout(new BorderLayout());		
            //TableModel tm = new TableModel();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());		
            //table = new JTable(model);
            table = new JTable();
            table.setModel(model);		
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setFillsViewportHeight(true);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
            String textvalue = textbox.getText();
            f2.add(scroll);
            f2.setVisible(true);
            f2.setSize(400,300);
        
        }
}