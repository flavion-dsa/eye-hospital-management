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
import org.crce.wtlabs.dto.Appointment;
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
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "INSERT INTO HR.DOCTORS VALUES (?,?,?,?,?,?)";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, d.getEmail());
            preparedStatement.setString(2, d.getFirstName());
            preparedStatement.setString(3, d.getLastName());
            preparedStatement.setString(4, d.getContact());
            preparedStatement.setString(5, d.getQualification());
            preparedStatement.setFloat(6, d.getSalary());
            
            preparedStatement.executeUpdate();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    @Override
    public List getAppointmemts(Doctor d) {
        List list = new ArrayList();
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.APPOINTMENTS WHERE D_ID = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, d.getEmail());
            result = preparedStatement.executeQuery();
            
            while(result.next()) {
                Appointment app = new Appointment();
                
                String find = "SELECT * FROM HR.PATIENTS WHERE EMAIL = ?";
                preparedStatement = conn.prepareStatement(find);
                preparedStatement.setString(1, result.getString("P_ID"));
                
                ResultSet rs = preparedStatement.executeQuery();
                String name = null;
                if(rs.next()) {
                    name = rs.getString("F_NAME")+" "+rs.getString("L_NAME");
                }
                app.setPatientName(name);
                app.setAppointmentDate(result.getDate("APP_DATE"));
                
                list.add(app);
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
