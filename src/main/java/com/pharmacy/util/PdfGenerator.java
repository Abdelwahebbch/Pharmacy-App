package com.pharmacy.util;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.pharmacy.Model.Prescription;

import java.io.FileNotFoundException;

public class PdfGenerator {
    public static void generatePrescriptionPDF(Prescription p, String filePath) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Ordonnance médicale").setBold().setFontSize(16));
            document.add(new Paragraph("Patient : " + p.getPatientName()));
            document.add(new Paragraph("Téléphone : " + p.getPatientPhone()));
            document.add(new Paragraph("Docteur : " + p.getDoctorName()));
            document.add(new Paragraph("Date d'émission : " + p.getIssueDate()));
            document.add(new Paragraph("Expiration : " + p.getExpiryDate()));
            document.add(new Paragraph("Médicaments :\n" + p.getMedications()));

            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
