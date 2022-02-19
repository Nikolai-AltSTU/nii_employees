package ru.stoiko.employees.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicationForm {

    private Long id;

    private String title;

    private String theAbstract;

    List<EmployeeForm> employees;
}
