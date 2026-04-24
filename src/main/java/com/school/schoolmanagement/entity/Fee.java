package com.school.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String status; // PAID / UNPAID

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}