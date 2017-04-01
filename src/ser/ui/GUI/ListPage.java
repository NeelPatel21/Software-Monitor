package ser.ui.GUI;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ser.admin.IntAdmin;

class ListPage extends JFrame {
    JButton[] jb;
    JLabel[] jl;
    IntAdmin ia;
    String uname=null;
    
    public String getname()
    {
        return ;
    }
    public String getUserName(LocalDate ld,IntAdmin ia){
        this.ia=ia;
        
        List<String> abc = ia.getAllUname(ld);
        int i=0;
        for(String username:abc)
        {
            jl[i] = new JLabel(username);
            add(jl[i]);
            jb[i] = new JButton("Get Details");
            add(jb[i]);
            jb[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   uname=getname();
                }
            });
            i++;
        }
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setLayout(new GridLayout(abc.size(), 1));
        //setVisible(true);
        return uname;
        
    }
}