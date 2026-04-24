package com.school.schoolmanagement.repository;

import com.school.schoolmanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}