package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.entity.Student;
import com.school.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // LIST ALL STUDENTS
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "student/list";
    }

    // SHOW ADD FORM
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    // SAVE (CREATE + UPDATE)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student,
                              RedirectAttributes redirectAttributes) {

        service.saveStudent(student);

        redirectAttributes.addFlashAttribute("successMessage",
                "Student saved successfully!");

        return "redirect:/students";
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        Student student = service.getStudentById(id);

        if (student == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Student not found!");
            return "redirect:/students";
        }

        model.addAttribute("student", student);
        return "student/form";
    }

    // DELETE STUDENT
    @GetMapping("/delete/{id}")
public String deleteStudent(@PathVariable Long id,
                            RedirectAttributes redirectAttributes) {

    System.out.println("Deleting ID: " + id);

    service.deleteStudent(id);

    redirectAttributes.addFlashAttribute("successMessage",
            "Student deleted successfully!");

    return "redirect:/students";
}
}