package ru.stoiko.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stoiko.employees.entity.Employee;

/**
 * Репозиторий для сущности Работника
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
