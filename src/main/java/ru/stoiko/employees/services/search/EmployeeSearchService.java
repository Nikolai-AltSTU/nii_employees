package ru.stoiko.employees.services.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.model.EmployeeModel;
import ru.stoiko.employees.repository.EmployeeRepository;
import ru.stoiko.employees.utils.mappers.EmployeeMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeSearchService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeModel> findAll() {
        log.info("[EmployeeSearchService]\tEntered findAll method");
        List<Employee> employeeList = employeeRepository.findAll(Sort.by("surname"));
        return EmployeeMapper.entitiesToForms(employeeList);
    }

    @Transactional(readOnly = true)
    public EmployeeModel findById(Long id) {
        log.info("[EmployeeSearchService]\tEntered findById method");
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(EmployeeMapper::entityToForm).orElse(null);
    }
}
