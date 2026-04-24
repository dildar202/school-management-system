package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.entity.Fee;
import com.school.schoolmanagement.repository.StudentRepository;
import com.school.schoolmanagement.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private StudentRepository studentRepository;

    // 📄 LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("fees", feeService.getAllFees());
        return "fees/list";
    }

    // ➕ FORM (NEW)
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("fee", new Fee());
        model.addAttribute("students", studentRepository.findAll());
        return "fees/form";
    }

    // 💾 SAVE
    @PostMapping
    public String save(@ModelAttribute Fee fee) {
        feeService.saveFee(fee);
        return "redirect:/fees";
    }

    // ✏️ EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Fee fee = feeService.getById(id);
        model.addAttribute("fee", fee);
        model.addAttribute("students", studentRepository.findAll());
        return "fees/form";
    }

    // 🗑 DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        feeService.deleteById(id);
        return "redirect:/fees";
    }
}