package ru.stoiko.employees.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PublicationModel {
    private Long id;
    private String title;
    private String theAbstract;
    //private List<EmployeeModel> employees;
}
