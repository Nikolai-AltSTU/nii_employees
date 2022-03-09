package ru.stoiko.employees.controllers.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.stoiko.employees.form.PublicationForm;
import ru.stoiko.employees.model.PublicationModel;
import ru.stoiko.employees.services.search.PublicationSearchService;

import java.util.List;

@Controller
@Slf4j
public class PublicationSearchController {

    @Autowired
    PublicationSearchService publicationSearchService;

    @GetMapping("/publications")
    public  String getAll(Model model)
    {
        log.info("[GET - /publications] \t PublicationSearchController.getAll() entered");
        List<PublicationModel> publicationModelList = publicationSearchService.findAll();
        model.addAttribute("publicationList", publicationModelList);
        model.addAttribute("publicationForm", new PublicationForm());
        model.addAttribute("publication", new PublicationModel());
        log.info("[GET - /publications] \t PublicationSearchController.getAll() executed");
        return "publication";
    }

    @GetMapping("/publications/{id}")
    public String getById(@PathVariable Long id, Model model)
    {
        log.info("[GET - /publications] \t PublicationSearchController.getById() entered");
        model.addAttribute("publication", publicationSearchService.findById(id));
        model.addAttribute("publicationForm", new PublicationForm());
        log.info("[GET - /publications] \t PublicationSearchController.getById() executed");
        return "publication"; // //new RedirectView("publication"); // redirect:
    }

    @ResponseBody
    @GetMapping("/publications/find")
    public ResponseEntity<?> getByTitle(@RequestBody String nameFragmentJSON)
    {
        try{
            String title = new ObjectMapper()
                    .readValue(nameFragmentJSON, ObjectNode.class)
                    .get("title")
                    .textValue();
            PublicationModel publicationModel = publicationSearchService.findByTitle(title);
            log.info("[GET - /publications/find] \t PublicationSearchController.findByTitle() entered");
            return ResponseEntity.ok(publicationModel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @GetMapping("/publications/findAll")
    public ResponseEntity<?> getAll()
    {
        try{
            List<PublicationModel> publicationModels = publicationSearchService.findAll();
            log.info("[GET - /publications/find] \t PublicationSearchController.findAll() entered");
            return ResponseEntity.ok(publicationModels);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
