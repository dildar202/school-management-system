package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.repository.AttendanceRepository;
import com.school.schoolmanagement.repository.FeeRepository;
import com.school.schoolmanagement.repository.StudentRepository;
import com.school.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private FeeRepository feeRepository;

    @GetMapping("/")
    public String dashboard(Model model) {

        // Basic counts
        long totalStudents = studentRepository.count();
        long totalTeachers = teacherRepository.count();

        // Attendance
        long totalAttendance = attendanceRepository.count();

        long presentCount = attendanceRepository.findAll()
                .stream()
                .filter(a -> "Present".equalsIgnoreCase(a.getStatus()))
                .count();

        long attendancePercent = 0;
        if (totalAttendance > 0) {
            attendancePercent = (presentCount * 100) / totalAttendance;
        }

        // Fees
        long totalFeeRecords = feeRepository.count();

        double totalCollected = feeRepository.findAll()
                .stream()
                .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                .mapToDouble(f -> f.getAmount())
                .sum();

        double totalPending = feeRepository.findAll()
                .stream()
                .filter(f -> "UNPAID".equalsIgnoreCase(f.getStatus()))
                .mapToDouble(f -> f.getAmount())
                .sum();

        long paidRecords = feeRepository.findAll()
                .stream()
                .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                .count();

        // Send to UI
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("totalTeachers", totalTeachers);

        model.addAttribute("attendancePercent", attendancePercent);
        model.addAttribute("presentToday", presentCount);
        model.addAttribute("totalAttendance", totalAttendance);

        model.addAttribute("totalFeeRecords", totalFeeRecords);
        model.addAttribute("totalCollected", totalCollected);
        model.addAttribute("totalPending", totalPending);
        model.addAttribute("paidRecords", paidRecords);

        return "dashboard/index";
    }
}