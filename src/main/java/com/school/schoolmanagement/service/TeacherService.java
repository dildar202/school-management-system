package com.school.schoolmanagement.service;

import com.school.schoolmanagement.entity.Teacher;
import com.school.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public Teacher getTeacherById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }
}