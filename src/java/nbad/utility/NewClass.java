/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbad.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pc
 */
public class NewClass {
    public static void main(String[] args) {
          Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            // get a connection
            String dbURL = "jdbc:mysql://localhost:3306/murach";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
