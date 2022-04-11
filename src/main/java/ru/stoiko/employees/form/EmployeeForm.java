package ru.stoiko.employees.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class EmployeeForm {

    /**
     * ID сотрудника
     */
    private Long id;
    /**
     * Фамилия
     */
    private String surname;
    /**
     * Имя
     */
    private String name;
    /**
     * Отчество
     */
    private String fathername;

    /**
     * Краткая биография сотрудника
     */
    private String biography;

    /**
     * Наименование должности сотрудника
     */
    private String positionName;

    /**
     * Описание интересов сотрудника
     */
    private String interests;

    /**
     * Путь к фото
     */
    private String photoPath;

    /**
     * Фотография сотрудника
     */
    private Optional<MultipartFile> photo;

    public void setPhoto(MultipartFile multipartFile) {
        photo = Optional.ofNullable(multipartFile);
    }

    private List<PublicationForm> publicationFormList;

}

