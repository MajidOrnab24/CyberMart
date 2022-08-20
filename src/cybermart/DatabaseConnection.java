/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cybermart;

/**
 *
 * @author majid
 */

import java.sql.Connection;
import java.sql.DriverManager;

// creating a class to link database with my sql and vice-versa.

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {

        String databaseName = "cybermart";
        String databaseUser = "root";
        String databasePassword = "majid205";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;

    
    }
}