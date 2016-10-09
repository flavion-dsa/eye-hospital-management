/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import org.crce.wtlabs.dto.Patient;

/**
 *
 * @author Flav
 */
public interface PatientDao {
    
    public void addPatient(Patient p);
    public int removePatient(Patient p);
    
}
