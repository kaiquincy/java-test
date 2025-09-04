package com.example.salarymanagement.controller;

import com.example.salarymanagement.entity.Employee;
import com.example.salarymanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/", "/employees"})
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "success", required = false) String success) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("employees", service.searchByName(keyword));
        } else {
            model.addAttribute("employees", service.getAllEmployees());
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("success", success);
        return "employees";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult result,
                       RedirectAttributes ra) {
        if (service.isNameTaken(employee.getName(), employee.getId())) {
            result.rejectValue("name", "duplicate", "Tên đã tồn tại");
        }
        if (result.hasErrors()) {
            return "form";
        }
        service.save(employee);
        ra.addAttribute("success", employee.getId() == null ? "created" : "updated");
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.getById(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        service.deleteById(id);
        ra.addAttribute("success", "deleted");
        return "redirect:/employees";
    }
}
