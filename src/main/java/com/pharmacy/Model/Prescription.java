package com.pharmacy.Model;

import java.time.LocalDate;

public class Prescription {
    private String patientName;
    private String doctorName;
    private String medications;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    
    public Prescription(String patientName, String doctorName, String medications, LocalDate issueDate, LocalDate expiryDate) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medications = medications;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }
    
    // Getters and setters
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
