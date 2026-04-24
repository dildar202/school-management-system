package com.school.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subject;
    private String email;
    private String phone;
}