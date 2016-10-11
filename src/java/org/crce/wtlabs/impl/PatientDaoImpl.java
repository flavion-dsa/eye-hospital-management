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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crce.wtlabs.dao.PatientDao;
import org.crce.wtlabs.dto.Patient;
import org.crce.wtlabs.dto.User;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class PatientDaoImpl extends UserDaoImpl implements PatientDao {

    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    
    @Override
    public void addPatient(Patient p) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "INSERT INTO HR.PATIENTS VALUES(?,?,?,?)";
        
        try {
            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, p.getEmail());
            preparedStatement.setString(2, p.getFirstName());
            preparedStatement.setString(3, p.getLastName());
            preparedStatement.setString(4, p.getContact());
            
            preparedStatement.executeUpdate();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public int removePatient(Patient p) {
        int count = 0;
        
        return count;
    }

}
