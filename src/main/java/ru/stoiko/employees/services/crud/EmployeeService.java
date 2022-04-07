package ru.stoiko.employees.services.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.repository.EmployeeRepository;
import ru.stoiko.employees.utils.mappers.EmployeeMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("priorityId")));
    }

    public Employee save(EmployeeForm employeeForm)
    {
        Employee employee;
        try {
            employee = EmployeeMapper.formToEntity(employeeForm);

            return employeeRepository.save(employee);
        }
        catch (Exception exception){
            log.error(exception.toString());
        }
        return null;
    }

    public Employee save(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public  Employee update(Employee employee) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        if (employeeOptional.isPresent()) {
            Employee employeeNew = employeeOptional.get();
            employeeNew.setSurname(employee.getSurname());
            employeeNew.setName(employee.getName());
            employeeNew.setFathername(employee.getFathername());
            employeeNew.setBiography(employee.getBiography());
            employeeNew.setInterests(employee.getInterests());
            employeeNew.setPositionName(employee.getPositionName());
            employeeRepository.save(employeeNew);
            return employeeNew;
        }
        else
            throw new Exception("Employee[id = " + employee.getId() + "] could not find in repository");
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
