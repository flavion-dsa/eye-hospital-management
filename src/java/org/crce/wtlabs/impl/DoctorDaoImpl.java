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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crce.wtlabs.dao.DoctorDao;
import org.crce.wtlabs.dto.Doctor;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class DoctorDaoImpl extends UserDaoImpl implements DoctorDao {
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    
    @Override
    public void addDoctor(Doctor d) {
        
    }

    @Override
    public int removeDoctor(Doctor d) {
        return 0;
    }

    @Override
    public List showAllDoctors() {
        
        List<Doctor> list = new ArrayList<>();
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.DOCTORS";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            result = preparedStatement.executeQuery();
            while (result.next()) {
                Doctor d = new Doctor();
                d.setFirstName(result.getString("F_NAME"));
                d.setLastName(result.getString("L_NAME"));
                d.setContact(result.getString("CONTACT"));
                d.setEmail(result.getString("EMAIL"));
                d.setQualification(result.getString("QUALIFICATION"));
                
                list.add(d);
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
