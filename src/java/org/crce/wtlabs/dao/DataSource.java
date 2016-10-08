/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flav
 */
public class DataSource {
    public static Connection conn;
    private static String url = "jdbc:oracle:thin:@//localhost:1521";
    private static String username = "admin";
    private static String password = "admin";
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    
    public static Connection getConnection() {
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
