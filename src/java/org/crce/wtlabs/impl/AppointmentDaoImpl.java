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
import org.crce.wtlabs.dao.AppointmentDao;
import org.crce.wtlabs.dto.Appointment;
import org.crce.wtlabs.dto.Doctor;
import org.crce.wtlabs.util.DataSource;

/**
 *
 * @author Flav
 */
public class AppointmentDaoImpl implements AppointmentDao {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    
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

            while (result.next()) {
                Appointment app = new Appointment();

                String find = "SELECT * FROM HR.PATIENTS WHERE EMAIL = ?";
                preparedStatement = conn.prepareStatement(find);
                preparedStatement.setString(1, result.getString("P_ID"));

                ResultSet rs = preparedStatement.executeQuery();
                String name = null;
                if (rs.next()) {
                    name = rs.getString("F_NAME") + " " + rs.getString("L_NAME");
                }
                app.setPatientEmail(result.getString("P_ID"));
                app.setPatientName(name);
                app.setAppointmentDate(result.getDate("APP_DATE"));
                app.setDoctorEmail(result.getString("D_ID"));

                list.add(app);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DoctorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
