package ru.stoiko.employees.controllers.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.form.PublicationForm;
import ru.stoiko.employees.services.crud.PublicationService;

import java.util.List;

@Controller
@Slf4j
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    /**
     *  Метод удаления данных о сотруднике
     * @param id
     * @return
     */
    @GetMapping("/publication_delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        log.info("[GET - /publication_delete/id{id}]\tEntered addEmployee method");
        publicationService.delete(id);
        return "redirect:/publication"; // redirect:
    }

    /**
     * Метод обработки запроса на добавление публикации
     * @param publicationForm
     * @return
     */
    @PostMapping("/publication_add")
    public String addPublication(@ModelAttribute PublicationForm publicationForm)//, @RequestParam("photoEmployee") MultipartFile photo)
    {
        log.info("[POST - /publications/add]\tEntered addEmployee method");
        try {
            publicationService.save(publicationForm);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        log.info("[POST - /publications/add]\tExit addEmployee method");
        return "redirect:/publication";
    }

    /**
     * Метод для обновления сведений о публикации
     * @param id
     * @param publicationForm
     * @return
     */
    @PostMapping("/publication_update/{id}")
    public String updatePublication(@PathVariable Long id, @ModelAttribute PublicationForm publicationForm)
    {
        log.info("[POST - /publication_update/{id}]\tExit updateEmployee method");
        publicationService.save(publicationForm);
        log.info("[POST - /publication_update/{id}]\tExit updateEmployee method");
        return "redirect:/publication";
    }

}
