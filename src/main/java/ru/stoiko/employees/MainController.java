package ru.stoiko.employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.form.PublicationForm;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String loadStartPage() {
        log.info("[GET - /]\tLoaded index page");
        return "index";
    }

    /*
    @GetMapping("/publication")
    public String loadPublicationPage(Model model)
    {
        model.addAttribute("publicationForm", new PublicationForm());
        log.info("[GET - /publication]\tLoaded publication page");
        return "publication";
    }*/

    @GetMapping("/administration")
    public String loadAdministrationPage(Model model)
    {
        log.info("[GET - /administration]\tLoaded administration page");
        model.addAttribute("employeeForm", new EmployeeForm());
        return "administration";
    }

}
