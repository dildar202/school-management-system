package com.school.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String status; // Present / Absent

    // 🔥 REAL RELATION WITH STUDENT
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}