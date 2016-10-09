/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Flav
 */
public class DataSource {
    private static Connection conn;
    private static String url = "jdbc:oracle:thin:@//localhost:1521";
    private static String username = "admin";
    private static String password = "admin";
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
