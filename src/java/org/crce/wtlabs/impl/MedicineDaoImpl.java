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
import org.crce.wtlabs.dao.MedicineDao;
import org.crce.wtlabs.dto.Medicine;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class MedicineDaoImpl implements MedicineDao {
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    @Override
    public void addMedicine(Medicine m) {
        
    }

    @Override
    public void updateMedicine(Medicine m) {
        
    }

    @Override
    public List showAllMedicines() {
        
        List<Medicine> list = new ArrayList<>();
        
        try {
            conn = DataSource.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM HR.MEDICINES";
        
        try {
            preparedStatement = conn.prepareStatement(query);
            
            result = preparedStatement.executeQuery();
            
            while(result.next()) {
                Medicine m = new  Medicine();
                m.setId(result.getInt("M_ID"));
                m.setName(result.getString("M_NAME"));
                m.setType(result.getString("M_TYPE"));
                m.setPrice(result.getFloat("PRICE"));
                m.setQuantity(result.getInt("QUANTITY"));
                
                list.add(m);
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
}
