package ru.stoiko.employees.controllers.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.services.crud.EmployeeService;

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/employee_delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        log.info("[GET - /employee_delete/id{id}]\tEntered addEmployee method");
        employeeService.delete(id);
        return "/employee"; // redirect:
    }

    /**
     * Метод обработки запроса на добавление сотрудника
     * @param employeeForm
     * @return
     */
    @PostMapping("/employee_add")
    public String addEmployee(@ModelAttribute EmployeeForm employeeForm)//, @RequestParam("photoEmployee") MultipartFile photo)
    {
        log.info("[POST - /employees/add]\tEntered addEmployee method");
        try {
            //employeeForm.setEmployeePhoto(photo);
            employeeService.save(employeeForm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[POST - /employees/add]\tExit addEmployee method");
        return "/employee"; // redirect:
    }

    @PostMapping("/employee_update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute EmployeeForm employeeForm)
    {
        log.info("[POST - /employee_update/{id}]\tExit updateEmployee method");
        employeeService.save(employeeForm);
        log.info("[POST - /employee_update/{id}]\tExit updateEmployee method");
        return "/employee";  // redirect:
    }

}
