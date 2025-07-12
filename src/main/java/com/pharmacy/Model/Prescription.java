package com.pharmacy.Model;

import java.sql.Date;

public class Prescription {
    private String patientPhone;
    private String patientName;
    private String doctorName;
    private Date issueDate;
    private Date expiryDate;
    private String status;
    private String medications;

    // Constructeur
    
    public Prescription(String patientPhone, String patientName, String doctorName, Date issueDate, Date expiryDate, String status, String medications) {
        this.patientPhone = patientPhone;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.medications = medications;
    }
    

    public String getPatientPhone() { return patientPhone; }
    public void setPatientPhone(String patientPhone) {this.patientPhone = patientPhone;}

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) {this.patientName = patientName;}

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) {this.doctorName = doctorName;}

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) {this.issueDate = issueDate;}

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) {this.expiryDate = expiryDate;}
   
    public String getStatus() { return status; }
    public void setStatus(String status) {this.status = status;}

    public String getMedications() { return medications; }
    public void setMedications(String medications) {this.medications = medications;}

    @Override
    public String toString() {
        return patientName + " (" + issueDate + ")";
    }

}
