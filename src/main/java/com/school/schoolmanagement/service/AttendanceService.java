package com.school.schoolmanagement.service;

import com.school.schoolmanagement.entity.Attendance;
import com.school.schoolmanagement.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    public List<Attendance> getAllAttendance() {
        return repository.findAll();
    }

    public Attendance saveAttendance(Attendance attendance) {
        return repository.save(attendance);
    }

    public void deleteAttendance(Long id) {
        repository.deleteById(id);
    }
}