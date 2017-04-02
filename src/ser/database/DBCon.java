/*
 * Copyright 2017 Sony.
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
package ser.database;

import com.dataBean.DataBeans;
import com.dataBean.IntDataBean;
import com.dataBean.IntDataTuple;
import java.time.LocalDate;
import java.util.List;
import ser.db.IntDataBase;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ser.logger.IntLogger;
/**
 *
 * @author Sony
 */
public class DBCon implements IntDataBase,IntLogger {
    private static final String dateformat= "yyyy-MM-dd";
    private static Connection con = null;
    private static String msg = "";
    
    
    public boolean close()
    {
        try{
        con.close();
        con = null;
        return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    
    public static DBCon getDBObject(String dbname,String uname,String password) 
    {
        DBCon db = null;
           try{
            db = new DBCon(dbname,uname,password);
           }catch(ClassNotFoundException | SQLException  e)
           {
               System.err.println(e);
               return null;
           }
           try {
               
         
         DatabaseMetaData md = con.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        boolean flag = true;
        while (rs.next()) {
            String x = rs.getString(3);
            System.out.println(x);
        if (x.equals("logtab"))
        {
            flag = false;
            
        }
        }
        if(flag)
        {
        Statement stmt=con.createStatement();
         // System.out.println("hell oworld");   
        stmt.execute("create table logtab(logdate date, username varchar(40), ip varchar(30), displayname varchar(100), mac varchar(30), version varchar(20), insdate date");    
        }
    
           }catch(Exception e)
           {
               System.err.println(e);
               return null;
           }
           return db;
    }
    
    public List<IntDataTuple> getAuth(){
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
            ResultSet rs=null;
        try {
            String d = LocalDate.now().format(DateTimeFormatter.ofPattern(dateformat));
            rs = stmt.executeQuery("select DISTINCT diplayname, version from logtab where logdate=CURDATE() order by displayname asc");
        } catch (SQLException ex) {
            return new ArrayList<>();
        }
        List<IntDataTuple> dt = new ArrayList<IntDataTuple>();
        try {    
            while(rs.next())
                dt.add(DataBeans.getDataTuple(rs.getString("displayname"),rs.getString("version"),null));
        } catch (SQLException ex1) {
            return new ArrayList<>();
        }
        return dt;
    }

    public DBCon(String dbname,String uname,String password) throws ClassNotFoundException, SQLException
    {
            Class.forName("com.mysql.jdbc.Driver"); 
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname,uname,password);
    }
    
    
    /*public String message(String msg)
    {
        return msg;
    }*/

    /*public static void main(String[] args)
    {
        System.out.println(getDBObject("temp","root","").log(null));
     
    }*/
    
    @Override
    public List<IntDataBean> getAllUserDetail(LocalDate date) {
           return null;
    }

    @Override
    public List<IntDataBean> getAllUserDetail(LocalDate sd, LocalDate ed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IntDataBean> getUserDetail(String user, LocalDate sd, LocalDate ed) {
        Statement stmt = null;
        List<IntDataBean> ldb = new ArrayList<IntDataBean>(); 
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            ResultSet rs=null;
        try {
            String d = (sd.format(DateTimeFormatter.ofPattern(dateformat)));
            rs = stmt.executeQuery("select * from logtab where logdate='"+d+"' and username='"+user+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            List<IntDataTuple> dt = new ArrayList<IntDataTuple>();
            try {    
                while(rs.next())
                {
                    dt.add(DataBeans.getDataTuple(rs.getString("displayname"),rs.getString("version"),LocalDate.parse(rs.getDate("insdate").toString(), DateTimeFormatter.ofPattern(dateformat))));
                }   } catch (SQLException ex1) {
                Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex1);
            }
            ldb.add(DataBeans.getNewDataBean(dt, user, sd));
            
            
            return ldb;
        }
        
        return null;
    }

    @Override
    public List<IntDataBean> getSoftDetail(String soft, LocalDate sd, LocalDate ed) {
        return null;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeSoft(String soft) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeLogs(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeLogsUpto(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllUserName(LocalDate date) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Statement stmt = null;
           List<String> li = new ArrayList<String>();
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            ResultSet rs=null;
        try {
            String d = (date.format(DateTimeFormatter.ofPattern(dateformat)));
            rs = stmt.executeQuery("select username from logtab where logdate='"+d+"'");
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        /*try {
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        try {
            while(rs.next())
             {
                
                //DataTuple db = DataBeans.getDataBean(sd, msg, date);
            //System.out.println(rs.getDate(1));
                 String un = rs.getString("username");
                 if(!(li.contains(un)))
                    li.add(un);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(li);
        return li;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean log(IntDataBean db) {
        if(db == null)
            return false;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }catch(Exception e)
        {
            return false;
        }
        try{
            for (IntDataTuple dt:db.getSoftDetail()){
                String d = "";
                String d1 = "";        
                try{ 
                d =  (db.getTime().format(DateTimeFormatter.ofPattern(dateformat)));
                 d1 = (dt.getDate().format(DateTimeFormatter.ofPattern(dateformat)));
                }catch(Exception e)
                {
                    return false;
                }
         /*         String d = "1000-01-01";
                  String d1 = "1000-02-02";
                  String name = "hello world";
                  String ip = "127.0.0.1";
                  String soft = "oracle";
                  String mac = "10-20-30-40-50-60";
                  String ver = "1.2.3.4.5";
             stmt.execute("insert into logtab values('"+d+"','"+name+"','"+ip+"','"+soft+"','"+mac+"','"+ver+"','"+d1+"')");   */
                stmt.execute("insert into logtab values('"+d+"','"+db.getName()+"','"+db.getIP()+"','"+dt.getSoftName()+"','"+db.getMac()+"','"+dt.getVersion()+"','"+d1+"')");
            }
            } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            }catch(Exception e)
            {
                System.err.println(e);
                return false;
            }
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    /*}   catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    
}
