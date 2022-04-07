package ru.stoiko.employees.services.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.stoiko.employees.entity.Publication;
import ru.stoiko.employees.form.PublicationForm;
import ru.stoiko.employees.repository.PublicationRepository;
import ru.stoiko.employees.utils.mappers.PublicationMapper;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    public List<Publication> getAll() {
        return publicationRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("priorityId")));
    }

    public Publication save(PublicationForm publicationForm)
    {
        Publication publication = PublicationMapper.formToEntity(publicationForm);
        return publicationRepository.save(publication);
    }

    public Publication save(Publication employee)
    {
        return publicationRepository.save(employee);
    }

    public  Publication update(Publication publication1) throws Exception {
        Optional<Publication> publicationOptional = publicationRepository.findById(publication1.getId());

        if (publicationOptional.isPresent()) {
            Publication publication = publicationOptional.get();
            publication.setTitle(publication1.getTitle());
            publication.setTheAbstract(publication1.getTheAbstract());
            publication.setEmployees(publication1.getEmployees());
            publicationRepository.save(publication);
            return publication;
        }
        else
            throw new Exception("Publication[id = " + publication1.getId() + "] could not find in repository");
    }

    public void delete(Long id) {
        publicationRepository.deleteById(id);
    }
}
