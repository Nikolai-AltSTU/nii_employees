package ru.stoiko.employees.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PublicationModel {
    private Long id;
    private String title;
    private String theAbstract;
    List<EmployeeModel> employees;
}
