package jp.co.axa.apidemo.test.util;

import java.util.ArrayList;
import java.util.List;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entity.Employee;

/**
 * Base test class for basic data
 *
 */
public class BaseTest {
	
	public static EmployeeDTO buildEmployeeDTO()
	{
		EmployeeDTO employee = new EmployeeDTO();
		employee.setId(1l);
		employee.setName("Mark");
		employee.setSalary(20000);
		employee.setDepartment("IT");
		return employee;
	}
	
	public static Employee buildEmployeeEntity()
	{
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Mark");
		employee.setSalary(20000);
		employee.setDepartment("IT");
		return employee;
	}
	
	

}
