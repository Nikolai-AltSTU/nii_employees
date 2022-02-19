package ru.stoiko.employees.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
     * Фотография сотрудника
     */
    private MultipartFile photo;
}

