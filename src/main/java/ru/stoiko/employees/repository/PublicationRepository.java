package ru.stoiko.employees.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stoiko.employees.entity.Publication;

/**
 * Репозиторий для сущности Публикации
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    /**
     *
     * @param title
     * @return Publication
     */
    Publication findByTitle(String title);
}
