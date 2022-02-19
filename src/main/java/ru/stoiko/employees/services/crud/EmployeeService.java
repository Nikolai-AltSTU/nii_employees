package ru.stoiko.employees.services.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.form.EmployeeForm;
import ru.stoiko.employees.repository.EmployeeRepository;
import ru.stoiko.employees.utils.mappers.EmployeeMapper;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("priorityId")));
    }

    public Employee save(EmployeeForm employeeForm)
    {
        Employee employee = EmployeeMapper.formToEntity(employeeForm);
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
