/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dto;

/**
 *
 * @author Flav
 */
public class Prescription {
    private String patientEmail;
    private String doctorEmail;
    private Medicine[] medicines;

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicine[] medicines) {
        this.medicines = medicines;
    }
    
    
}
