package ser.ui.GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import ser.admin.IntAdmin;

class ListPage extends JFrame {
    JButton[] jb;
    JLabel[] jl;
    IntAdmin ia;
    String uname=null;
    
    public ListPage(IntAdmin ia)
    {
        this.ia = ia;
    }
    
    public String getname(Object x)
    {
       // if(!(x instanceof JButton))
         //   return null;
        for(int i=0;i<jb.length;i++){
            if(jb[i]==x)
                uname=jl[i].getText();
        }
        return uname;
    }
    
    public String getUserName(LocalDate ld,IntAdmin ia){
        
        this.ia=ia;
        
        //System.out.println("listpage: "+ld+ia);
        //System.out.println(ld);
        //System.out.println(ia);
        List<String> abc ;//new ArrayList<String>();
        
        //abc.add("hello");
        //abc.add("world");
        abc = ia.getAllUname(ld);
        //for(int k=0;k<10;k++)
        //abc.add("hello");
        //abc.add("world");
        //System.out.println(abc);
        int i=0;
        jl = new JLabel[abc.size()];
        jb = new JButton[abc.size()];
        for(String username:abc)
        {
            jl[i] = new JLabel(username);
            jl[i].setVerticalAlignment(SwingConstants.CENTER);
            add(jl[i]);
            jb[i] = new JButton("Get Details");
            jb[i].setVerticalAlignment(SwingConstants.CENTER);
            add(jb[i]);
            jb[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   String name =  getname(e.getSource());
                   DataPage dp = new DataPage(ia,name,ld);
                }
            });
            i++;
        }
        System.out.println("hello world  at end");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(xSize,ySize);
        setLayout(new GridLayout(abc.size(), 1));
        return uname;
    }
}