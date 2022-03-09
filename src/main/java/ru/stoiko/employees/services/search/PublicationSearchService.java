package ru.stoiko.employees.services.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stoiko.employees.entity.Publication;
import ru.stoiko.employees.model.PublicationModel;
import ru.stoiko.employees.repository.PublicationRepository;
import ru.stoiko.employees.utils.mappers.PublicationMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublicationSearchService {

    @Autowired
    PublicationRepository publicationRepository;

    @Transactional(readOnly = true)
    public List<PublicationModel> findAll() {
        log.info("[PublicationSearchService]\tEntered findAll method");
        List<Publication> publicationList = publicationRepository.findAll(Sort.by("title"));
        return PublicationMapper.entitiesToModels(publicationList);
    }

    @Transactional(readOnly = true)
    public PublicationModel findById(Long id) {
        log.info("[PublicationSearchService]\tEntered findById method");
        Optional<Publication> publication = publicationRepository.findById(id);
        return publication.map(PublicationMapper::entityToModel).orElse(null);
    }

    @Transactional(readOnly = true)
    public PublicationModel findByTitle(String title) {
        log.info("[PublicationSearchService]\tEntered findById method");
        Optional<Publication> publication = Optional.ofNullable(publicationRepository.findByTitle(title));
        return publication.map(PublicationMapper::entityToModel).orElse(null);
    }
}
