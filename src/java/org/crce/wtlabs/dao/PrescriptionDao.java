/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import java.util.List;
import org.crce.wtlabs.dto.Patient;

/**
 *
 * @author Flav
 */
public interface PrescriptionDao {
    public void addPrecription();
    public List getPrescription(Patient patient);
    public int getBill(Patient patient);
}
