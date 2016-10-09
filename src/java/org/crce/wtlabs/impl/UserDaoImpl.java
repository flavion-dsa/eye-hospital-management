/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crce.wtlabs.dao.UserDao;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class UserDaoImpl implements UserDao {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    
    @Override
    public boolean isValid(User user) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM USER WHERE USERNAME = ?";  
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            
            result = preparedStatement.executeQuery();
            
            if(result.next()) {
                System.out.println(result.getString("PASSWORD"));
                
                if(result.getString("PASSWORD").equals(user.getPassword()) && result.getInt("VERIFIED") == 1) {
                    return true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public int getUserType(User user) {
        int type = 0;
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM USER WHERE USERNAME = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            
            result = preparedStatement.executeQuery();
            
            if(result.next()) {
                type = result.getInt("USER_TYPE");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return type;
    }
    
}
