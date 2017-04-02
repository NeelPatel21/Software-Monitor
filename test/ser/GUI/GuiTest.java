package ser.GUI;

import java.nio.file.Paths;
import ser.admin.Admin;
import ser.admin.IntAdmin;
import ser.db.IntDataBase;
import ser.db.logData.LogDataBase;
import ser.ui.GUI.StartPage;
import ser.ui.IntUI;

class GuiTest{
    public static void main(String[] args) {
        IntDataBase db=new LogDataBase(Paths.get("temp","log").toAbsolutePath());
        
        IntAdmin ia=new Admin(db);
        System.out.println(ia);
        IntUI cli=new StartPage(ia);
        cli.start();
    }
}