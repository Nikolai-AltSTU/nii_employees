package ru.stoiko.employees.controllers.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.services.crud.EmployeeService;

import java.util.Optional;

@Controller
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     *  Метод удаления данных о сотруднике
     * @param id
     * @return
     */
    @GetMapping("/employee_delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        log.info("[GET - /employee_delete/id{id}]\tEntered addEmployee method");
        employeeService.delete(id);
        return "redirect:/employee"; // redirect:
    }

    /**
     * Метод обработки запроса на добавление сотрудника
     * @param employeeForm
     * @return
     */
    @PostMapping("/employee_add")
    public String addEmployee(@ModelAttribute EmployeeForm employeeForm, @RequestParam("photoEmployee") Optional<MultipartFile> photo)
    {
        log.info("[POST - /employees/add]\tEntered addEmployee method");
        try {
            if(photo.isPresent() && photo.get().getSize() > 0)
                employeeForm.setPhoto(photo.get());
            employeeService.save(employeeForm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[POST - /employees/add]\tExit addEmployee method");
        return "redirect:/employee";
    }

    /**
     * Метод для обновления сведений о сотруднике
     * @param photo
     * @param employeeForm
     * @return
     */
    @PostMapping("/employee_update/")
    public String updateEmployeeWithPhoto(@RequestParam("photoEmployee") Optional<MultipartFile> photo, @ModelAttribute EmployeeForm employeeForm)
    {
        log.info("[POST - /employee_update/{id}]\tExit updateEmployee method");
        if(photo.isEmpty() || photo.get().getSize() == 0)
            employeeService.save(employeeForm);
        else {
            try {
                employeeForm.setPhoto(photo.get());
                employeeService.save(employeeForm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("[POST - /employee_update/{id}]\tExit updateEmployee method");
        return "redirect:/employee";
    }

}
