package com.school.schoolmanagement.service;

import com.school.schoolmanagement.entity.Student;
import com.school.schoolmanagement.repository.AttendanceRepository;
import com.school.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // GET ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // SAVE / UPDATE
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // GET BY ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // 🔥 FIXED DELETE METHOD
    @Transactional
    public void deleteStudent(Long id) {

        // delete child records first
        attendanceRepository.deleteByStudent_Id(id);

        // delete parent
        studentRepository.deleteById(id);
    }
}