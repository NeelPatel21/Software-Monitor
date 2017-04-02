package ser.ui.GUI;

import java.time.LocalDate;
import ser.admin.IntAdmin;

public class GuiFlow{
    IntAdmin ia = null;
   public GuiFlow(IntAdmin ia){
        ia=this.ia;
}
    LocalDate ld=null;
    String uname=null;
    public void start(){
        StartPage sp=new StartPage(ia);
        //ld=sp.getDate();
        //ListPage lp=new ListPage();
        //uname=lp.getUserName(ld,ia);
        DataPage dp=new DataPage(ia, uname, ld);
    }
   
}