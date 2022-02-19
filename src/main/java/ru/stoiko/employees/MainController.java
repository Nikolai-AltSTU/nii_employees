package ru.stoiko.employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stoiko.employees.form.EmployeeForm;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String loadStartPage() {
        log.info("[GET - /]\tLoaded index page");
        return "index";
    }

    @GetMapping("/employee")
    public String loadEmployeePage()
    {
        log.info("[GET - /employee]\tLoaded employee page");
        return "employee";
    }

    @GetMapping("/publication")
    public String loadPublicationPage()
    {
        log.info("[GET - /publication]\tLoaded publication page");
        return "publication";
    }

    @GetMapping("/administration")
    public String loadAdministrationPage(Model model)
    {
        log.info("[GET - /administration]\tLoaded administration page");
        model.addAttribute("employeeForm", new EmployeeForm());
        return "administration";
    }

}
