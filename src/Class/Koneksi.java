/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */ 

public class Koneksi {
    public static Connection koneksi;
        public Statement st;
         private static  com.mysql.jdbc.Connection con;
//    private static Connection con;
//    public static Connection getConnection(){
//        try{
//            con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/db_saw","root","");
//            Toolkit.getDefaultToolkit().beep();
//        }
//        catch(SQLException e){
//            JOptionPane.showMessageDialog(null, "Koneksi Gagal"+e.getMessage());
//        }
//        return con;
//    }
         public static Connection getConnection(){
        if(koneksi==null){
            try{
                String server="jdbc:mysql://localhost:3306/db_saw";
                String user="root";
                String password="";
                Class.forName("com.mysql.jdbc.Driver");
                koneksi=DriverManager.getConnection(server,user,password);               
            }catch(ClassNotFoundException | SQLException x){
                JOptionPane.showMessageDialog(null,"Koneksi Gagal, Pesan error \n"+x);
            }                        
        }
        return koneksi;
    }
     public void Koneksi(){
            try{
                getConnection();
                st=koneksi.createStatement();
            }catch(Exception x){
                JOptionPane.showMessageDialog(null,"Koneksi ambil Gagal, Pesan error \n"+x);
            }          
    }
    public ResultSet ambilData(String sql){
        ResultSet rs=null;
        try{
            Koneksi();
            rs=st.executeQuery(sql);
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,"Ambil Data Gagal, Pesan error : \n"+x);
        }
        return rs;
    }
    public void simpanData(String sql){
        try{            
            Koneksi();
            st.executeUpdate(sql);
        }catch(Exception x){
            //JOptionPane.showMessageDialog(null,"Simpan Data Gagal, Pesan error : \n"+x);
            System.out.print(x);
        }        
    }
}
