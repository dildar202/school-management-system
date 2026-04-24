package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.entity.Teacher;
import com.school.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("teachers", service.getAllTeachers());
        return "teacher/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/form";
    }

    @PostMapping
    public String save(@ModelAttribute Teacher teacher) {
        service.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", service.getTeacherById(id));
        return "teacher/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTeacher(id);
        return "redirect:/teachers";
    }
}