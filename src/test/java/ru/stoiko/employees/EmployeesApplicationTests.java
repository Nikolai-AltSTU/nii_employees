package ru.stoiko.employees;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.stoiko.employees.entity.Employee;
import ru.stoiko.employees.model.EmployeeModel;
import ru.stoiko.employees.repository.EmployeeRepository;
import ru.stoiko.employees.services.crud.EmployeeService;
import ru.stoiko.employees.services.search.EmployeeSearchService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class EmployeesApplicationTests {

	@Autowired
	EmployeeSearchService employeeSearchService;
	@Autowired
	EmployeeService employeeCrudService;
	@Autowired
	EmployeeRepository repository;

	@Test
	@DisplayName("Testing context connection")
	void contextLoads() {
		long c = 0;
		try {
			c = repository.count();
		}catch (Exception e)
		{
			assertTrue ("Не удалось подключиться к базе данных", false);
		}
	}

	@Test
	@DisplayName("Testing a crud-method save()")
	@Transactional
	void testSave() {
		int testSize = 50;
		List<Employee> list = new ArrayList<>();
		for (int i = 0; i < testSize; i++) {
			Employee employee = Employee.builder()
					.surname("surname" + i)
					.name("name" + i)
					.fathername("fathername" + i)
					.positionName("pos" + i)
					.interests("interests" + i)
					.biography("biography" + i)
					.build();
			list.add(employee);
			employeeCrudService.save(employee);
		}
		for (int i = 0; i < testSize; i++) {
			assertTrue( "Не удалось сохранить запись в базу", repository.findById(list.get(i).getId()).isPresent()) ;
			repository.deleteById(list.get(i).getId());
		}
	}


	@Test
	@DisplayName("Testing a crud-method update()")
	@Transactional
	void testUpdate() throws Exception {
		Employee employee = Employee.builder()
					.surname("surname")
					.name("name")
					.fathername("fathername")
					.positionName("pos")
					.interests("interests")
					.biography("biography")
					.build();
		employeeCrudService.save(employee);

		Employee newEmployee = Employee.builder()
				.id(employee.getId())
				.surname("surnameUp")
				.name("nameUp")
				.fathername("fathernameUp")
				.positionName("posUp")
				.interests("interestsUp")
				.biography("biographyUp")
				.build();
		employeeCrudService.update(newEmployee);

		assertTrue("Не найден сотрудник по id", repository.findById(employee.getId()).isPresent());
		assertEquals(newEmployee.getName(), repository.findById(employee.getId()).get().getName());
		assertEquals(newEmployee.getFathername(), repository.findById(employee.getId()).get().getFathername());
		assertEquals(newEmployee.getSurname(), repository.findById(employee.getId()).get().getSurname());
		assertEquals(newEmployee.getPositionName(), repository.findById(employee.getId()).get().getPositionName());
		assertEquals(newEmployee.getInterests(), repository.findById(employee.getId()).get().getInterests());
		assertEquals(newEmployee.getBiography(), repository.findById(employee.getId()).get().getBiography());

		repository.deleteById(employee.getId());
	}

	@Test
	@DisplayName("Testing a crud-method deleteById()")
	@Transactional
	void testDeleteById() {
		Employee employee = Employee.builder()
				.surname("surname")
				.name("name")
				.fathername("fathername")
				.positionName("pos")
				.interests("interests")
				.biography("biography")
				.build();
		employeeCrudService.save(employee);
		EmployeeModel employeeModel = employeeSearchService.findById(employee.getId());
		EmployeeModel employeeFinal = employeeModel;
		assertDoesNotThrow(() -> employeeCrudService.delete(employeeFinal.getId()));
	}

}
