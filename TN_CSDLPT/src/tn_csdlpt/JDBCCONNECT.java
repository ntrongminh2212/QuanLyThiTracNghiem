        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author zLittleMasterz
 */
public class JDBCCONNECT {
    public static Connection getConnection(String tenUser, String password, String tenServer) throws SQLException {
        Connection conn = null;
        String url = "jdbc:sqlserver://" +tenServer+";databaseName=TN_CSDLPT";
        conn = DriverManager.getConnection(url, tenUser, password);
        return conn;
    }
    
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:sqlserver://DESKTOP-P36CIA4;databaseName=TN_CSDLPT";
        String user = "dangnhap";
        String password = "123";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối jdbc");
        }
        return conn;
    }
}
