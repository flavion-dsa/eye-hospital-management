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
        
        String query = "SELECT * FROM HR.USERS WHERE USERNAME = ?";  
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            
            result = preparedStatement.executeQuery();
            
            if(result.next()) {
                System.out.println(result.getString("PASSWORD"));
                
                if(result.getString("PASSWORD").equals(user.getPassword())) {
                    return true;
                }
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public User getUser(String name) {
        User user = null;
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.USERS WHERE USERNAME = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            
            result = preparedStatement.executeQuery();
            
            if(result.next()) {
                user = new User();
                user.setType(result.getInt("USER_TYPE"));
                user.setVcode(result.getInt("VCODE"));
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

    @Override
    public void addUser(User user) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "INSERT INTO HR.USERS VALUES (?,?,?,?,?)";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getVcode());
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, user.getType());
            
            preparedStatement.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updatePassword(User user) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void updateUser(User user) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "UPDATE HR.USERS SET VERIFIED = 1 WHERE USERNAME = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public boolean isVerified(User user) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.USERS WHERE USERNAME = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, user.getName());
            result = preparedStatement.executeQuery();
            
            if(result.next()) {
                if(result.getInt("VERIFIED") == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
