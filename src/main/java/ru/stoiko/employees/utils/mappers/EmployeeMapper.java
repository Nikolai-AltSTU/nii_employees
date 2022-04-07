package ru.stoiko.employees.utils.mappers;


import lombok.extern.slf4j.Slf4j;
import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.model.EmployeeModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeMapper {

    /**
     * Статический метод преобразования сущности работника в модель для формы
     * @param employee
     * @return
     */
    public static EmployeeModel entityToModel(Employee employee)  {
        if(employee.getPhotoPath() == null)
            employee.setPhotoPath("/images/employee0.jpg");

        if(employee.getPhoto() != null)
        {
            final String folder = "/target/classes/static";
            final String filename = "/images/employee" + employee.getId().toString() + ".jpg";
            Path currentPath = Paths.get("");
            Path path = Paths.get(currentPath.toAbsolutePath().toString(), folder,  filename);
            log.info("Absolute path " + path.toAbsolutePath().toString());

            //String pathToFile = (path.toAbsolutePath().toString() + folder + filename);
            try {
                File photo = new File(String.valueOf(path));
                photo.setWritable(true);
                photo.setReadable(true, false);
                photo.createNewFile();
                Files.write(Path.of(photo.getPath()), employee.getPhoto());
            }
            catch (Exception exception)
            {
                log.error(exception.toString());
            }
            employee.setPhotoPath(filename);
        }
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .fathername(employee.getFathername())
                .surname(employee.getSurname())
                .biography (employee.getBiography())
                .interests(employee.getInterests())
                .positionName(employee.getPositionName())
                .photoPath(employee.getPhotoPath())
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
    public static Employee formToEntity(EmployeeForm employee) throws IOException {

        if(employee.getPhoto().isPresent() && employee.getPhoto().get().getSize() > 0)
            return Employee.builder()
                    .id(employee.getId())
                    .surname(employee.getSurname())
                    .name(employee.getName())
                    .fathername(employee.getFathername())
                    .positionName(employee.getPositionName())
                    .biography (employee.getBiography())
                    .interests(employee.getInterests())
                    .photo(employee.getPhoto().get().getBytes())
                    .build();
        else
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

    public static List<Employee> formToEntity(List<EmployeeForm> employees) {
        if(employees == null)
            employees = new ArrayList<EmployeeForm>();
        try {
            List<Employee> collect = new ArrayList<>();
            for (EmployeeForm employee : employees) {
                Employee formToEntity = formToEntity(employee);
                collect.add(formToEntity);
            }
            return collect;
        }
        catch (Exception exception)
        {
            return Collections.emptyList();
        }
    }
}
