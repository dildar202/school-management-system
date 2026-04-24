package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf/students")
    public ResponseEntity<InputStreamResource> downloadPdf() {

        ByteArrayInputStream bis = pdfService.generateStudentPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=students.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}