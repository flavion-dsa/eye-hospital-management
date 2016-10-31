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
import org.crce.wtlabs.dao.PaymentDao;
import org.crce.wtlabs.dto.Payment;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class PaymentDaoImpl implements PaymentDao {
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    @Override
    public void addPayment(Payment payment) {
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "INSERT INTO HR.PAYMENTS VALUES (?,?,?,?,?)";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, payment.getP_id());
            
            String find = "SELECT * FROM HR.PATIENTS WHERE EMAIL = ?";
            String name;
            try (PreparedStatement newStatement = conn.prepareStatement(find); 
                    ResultSet rs = newStatement.executeQuery()) {
                name = null;
                if (rs.next()) {
                    name = rs.getString("F_NAME") + " " + rs.getString("L_NAME");
                }              
            }
            
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, payment.getFees());
            preparedStatement.setString(4, payment.getModeOfPayment());
            preparedStatement.setDate(5, payment.getPaymentDate());
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List showAllPayments() {
        List<Payment> list = new ArrayList<>();
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.PAYMENTS";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            result = preparedStatement.executeQuery();
            
            while(result.next()){
                Payment payment = new Payment();
                
                payment.setP_id(result.getString("P_ID"));
                payment.setPatientName(result.getString("P_NAME"));
                payment.setFees(result.getInt("FEES"));
                payment.setModeOfPayment(result.getString("PAY_TYPE"));
                payment.setPaymentDate(result.getDate("PAY_DATE"));
                
                list.add(payment);
                    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return list;
    }
    
}
