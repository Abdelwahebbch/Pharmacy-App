package com.pharmacy.util;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.pharmacy.Model.Prescription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.text.StyleConstants.ColorConstants;

public class PdfGenerator {
    public static void generatePrescriptionPDF(Prescription p) {
        String directoryPath = "prescriptions/";
        String fileName = directoryPath + p.getPatientName().toLowerCase().replace(" ", "_")
                + "_" + LocalDate.now() + ".pdf";

        // Ensure directory exists
        new File(directoryPath).mkdirs();

        try (PdfWriter writer = new PdfWriter(fileName);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc)) {

            // Fonts and formatting
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            Paragraph title = new Paragraph("ORDONNANCE MÉDICALE")
                    .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(com.itextpdf.kernel.colors.ColorConstants.BLUE);
            document.add(title);

            document.add(new Paragraph("\nDate : " + LocalDate.now()));
            document.add(new Paragraph("Patient(e) : " + p.getPatientName()));
            document.add(new Paragraph("Téléphone : " + p.getPatientPhone()));
            document.add(new Paragraph("Médecin : " + p.getDoctorName()));
            document.add(new Paragraph("Status : " + p.getStatus()));
            document.add(new Paragraph("\nMédicaments prescrits :"));
            document.add(new Paragraph("- " + p.getMedications().replace(",", "\n- ")));

            document.add(new Paragraph("\nSignature : __________________").setMarginBottom(30)
                    .setTextAlignment(TextAlignment.RIGHT));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
