package ru.stoiko.employees.controllers.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.model.EmployeeModel;
import ru.stoiko.employees.services.search.EmployeeSearchService;

import java.util.List;

@Controller
@Slf4j
public class EmployeeSearchController {

    @Autowired
    EmployeeSearchService employeeSearchService;

    @GetMapping("/employee")
    public  String getAll(Model model)
    {
        log.info("[GET - /employees] \t EmployeeSearchController.getAll() entered");
        List<EmployeeModel> employeeList = employeeSearchService.findAll();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("employeeForm", new EmployeeForm());
        model.addAttribute("employee", new EmployeeModel());
        log.info("[GET - /employees] \t EmployeeSearchController.getAll() executed");
        return "employee";
    }

    @GetMapping("/employees/{id}")
    public  String getById(@PathVariable Long id, Model model)
    {
        log.info("[GET - /employees] \t EmployeeSearchController.getById() entered");
        model.addAttribute("employee", employeeSearchService.findById(id));
        model.addAttribute("employeeForm", new EmployeeForm());
        log.info("[GET - /employees] \t EmployeeSearchController.getById() executed");
        return "employee"; // redirect:
    }


}
