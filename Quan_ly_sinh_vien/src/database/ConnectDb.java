/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THAIHB.B19CN638
 */
public class ConnectDb {

    public Connection conn;
//   jdbc:mysql://localhost:3306/?user=root
    public ConnectDb() {
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                String url = "jdbc:mysql://localhost:3306/quan_ly_user";
                String user = "root";
                String password = "B19dccn638+";
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Bạn đang kết nối với database ...");
        } catch (Exception ex) {
           System.out.println("Lỗi, bạn không kết nối được với database");
           ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn; 
    }
  
}

