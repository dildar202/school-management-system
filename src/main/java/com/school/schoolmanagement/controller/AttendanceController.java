package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.entity.Attendance;
import com.school.schoolmanagement.repository.StudentRepository;
import com.school.schoolmanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @Autowired
    private StudentRepository studentRepository;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("records", service.getAllAttendance());
        return "attendance/list";
    }

    // FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentRepository.findAll());
        return "attendance/form";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@ModelAttribute Attendance attendance) {
        service.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    // DELETE (NO WHITE LABEL ERROR)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteAttendance(id);
        return "redirect:/attendance";
    }
}