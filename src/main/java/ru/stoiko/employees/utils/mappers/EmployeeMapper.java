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
    public static EmployeeModel entityToForm(Employee employee) {
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .fathername(employee.getFathername())
                .surname(employee.getSurname())
                .biography (employee.getBiography())
                //.photo("data:image/jpeg;base64," + imgTransformationUtils.byteToBase64(cityEntity.getPhoto()))
                .build();
    }

    public static List<EmployeeModel> entitiesToForms(List<Employee> employees) {
        return employees.stream().map(EmployeeMapper::entityToForm).collect(Collectors.toList());
    }

    /**
     * Статический метод преобразования модели формы в сущность работника
     * @param employee
     * @return
     */
    public static Employee formToEntity(EmployeeForm employee) {
        return Employee.builder()
                .id(employee.getId())
                .name(employee.getName())
                .fathername(employee.getFathername())
                .surname(employee.getSurname())
                .biography (employee.getBiography())
                //.photo("data:image/jpeg;base64," + imgTransformationUtils.byteToBase64(cityEntity.getPhoto()))
                .build();
    }
}
