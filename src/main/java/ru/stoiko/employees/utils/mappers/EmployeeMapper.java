package ru.stoiko.employees.utils.mappers;


import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.model.EmployeeModel;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    /**
     * Статический метод преобразования сущности работника в модель для формы
     * @param employee
     * @return
     */
    public static EmployeeModel entityToModel(Employee employee) {
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .fathername(employee.getFathername())
                .surname(employee.getSurname())
                .biography (employee.getBiography())
                .interests(employee.getInterests())
                .positionName(employee.getPositionName())
                .build();
    }

    public static List<EmployeeModel> entitiesToModels(List<Employee> employees) {
        return employees.stream().map(EmployeeMapper::entityToModel).collect(Collectors.toList());
    }

    /**
     * Статический метод преобразования модели формы в сущность работника
     * @param employee
     * @return
     */
    public static Employee formToEntity(EmployeeForm employee) {
        return Employee.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .name(employee.getName())
                .fathername(employee.getFathername())
                .positionName(employee.getPositionName())
                .biography (employee.getBiography())
                .interests(employee.getInterests())
                .build();
    }
}
