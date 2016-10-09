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
    private static String host = "jdbc:postgresql://localhost:5432/HR";
    private static String username = "postgres";
    private static String password = "pass";
    private static String driver = "org.postgresql.Driver";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(host, username, password);
        return conn;
    }
}
