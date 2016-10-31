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
import org.crce.wtlabs.dao.PrescriptionDao;
import org.crce.wtlabs.dto.Patient;
import org.crce.wtlabs.dto.Prescription;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class PrescriptionDaoImpl implements PrescriptionDao {

    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet result;
    
    @Override
    public void addPrecription() {
        
    }

    @Override
    public List getPrescription(Patient patient) {
        
        List<Prescription> list = new ArrayList<>();
        
        return list;
    }

    @Override
    public int getBill(Patient patient) {
        int totalBill = 0;
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrescriptionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT COUNT(PRICE) FROM HR.PRESCRIPTIONS NATURAL JOIN HR.MEDICINES WHERE P_ID = ? AND PRES_DATE = ?";
        
        try {
            preparedStatement = conn.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return totalBill;
    }
    
}
