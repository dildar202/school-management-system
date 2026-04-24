package com.school.schoolmanagement.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.school.schoolmanagement.entity.Student;
import com.school.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private StudentRepository studentRepository;

    public ByteArrayInputStream generateStudentPdf() {

        List<Student> students = studentRepository.findAll();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("School Students Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            // Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 3});

            // Headers
            Stream.of("ID", "Name", "Email", "Phone")
                    .forEach(header -> {
                        PdfPCell hcell = new PdfPCell();
                        hcell.setPhrase(new Phrase(header));
                        table.addCell(hcell);
                    });

            // Data
            for (Student s : students) {
                table.addCell(String.valueOf(s.getId()));
                table.addCell(s.getName());
                table.addCell(s.getEmail());
                table.addCell(s.getPhone());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}