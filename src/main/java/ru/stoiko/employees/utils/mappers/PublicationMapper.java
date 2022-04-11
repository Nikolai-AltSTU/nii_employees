package ru.stoiko.employees.utils.mappers;

import ru.stoiko.employees.entity.Publication;
import ru.stoiko.employees.form.PublicationForm;
import ru.stoiko.employees.model.PublicationModel;

import java.util.List;
import java.util.stream.Collectors;

public class PublicationMapper {

    /**
     * Статический метод преобразования сущности работника в модель для формы
     * @param publication
     * @return
     */
    public static PublicationModel entityToModel(Publication publication) {
        return PublicationModel.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .theAbstract(publication.getTheAbstract())
                //.employees(EmployeeMapper.entitiesToModels(publication.getEmployees()))
                .build();
    }

    public static PublicationModel entityToModelWithOutEmployees(Publication publication) {
        return PublicationModel.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .theAbstract(publication.getTheAbstract())
                .build();
    }

    public static List<PublicationModel> entitiesToModels(List<Publication> publications) {
        return publications.stream().map(PublicationMapper::entityToModel).collect(Collectors.toList());
    }

    public static List<PublicationModel> entitiesToModelsWithOutEmployees(List<Publication> publications) {
        return publications.stream().map(PublicationMapper::entityToModelWithOutEmployees).collect(Collectors.toList());
    }

    /**
     * Статический метод преобразования модели формы в сущность работника
     * @param publication
     * @return
     */
    public static Publication formToEntity(PublicationForm publication) {
        return Publication.builder()
                .id(publication.getId())
                .title(publication.getTitle())
                .theAbstract(publication.getTheAbstract())
                //.employees(EmployeeMapper.formToEntity(publication.getEmployees()))
                .build();
    }
}
