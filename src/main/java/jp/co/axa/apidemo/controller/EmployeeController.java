package jp.co.axa.apidemo.controller;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.mapper.EmployeeMapper;
import jp.co.axa.apidemo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Retrieve all employees
	 * 
	 * @Param
	 * @return Response Entity response
	 */
	@GetMapping("/employees")
	@ApiOperation(value = "Fetch all Employess", notes = "Retrieve all employees from Employee table", response = Employee.class)
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> emp = employeeService.retrieveEmployees();
		return ResponseEntity.ok(emp);

	}

	/**
	 * Get employee by employee Id
	 * 
	 * @Param long EmployeeId
	 * @return Response Entity response
	 */
	@GetMapping("/employees/{employeeId}")
	@ApiOperation(value = "Fetch Employee with Employee Id", notes = "Retrieve employee with the given employee id", response = Employee.class)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId)
			throws ResourceNotFoundException {
		Optional<EmployeeDTO> employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employee.get());
	}

	/**
	 * Create new employee.
	 * 
	 * @Param EmployeeDTO employeeDetails
	 * @return Response Entity response
	 */
	@PostMapping("/employees")
	@ApiOperation(value = "Create new Employee record", notes = "Create a new employee record", response = Employee.class)
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDetails) {
		employeeService.createEmployee(employeeDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeDetails);
	}

	/**
	 * Create new employee.
	 * 
	 * @Param long employeeId
	 * @return Response Entity response
	 */
	@DeleteMapping("/employees/{employeeId}")
	@ApiOperation(value = "Delete employee with given Employee ID", notes = "Delete the employee", response = Employee.class)
	public ResponseEntity delete(@PathVariable Long employeeId) throws ResourceNotFoundException {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	/**
	 * Update employee with employeeDetails
	 * 
	 * @Param long employeeId
	 * @Param EmployeeDTO employeeDetails
	 * @return Response Entity response
	 */
	@PutMapping("/employees/{employeeId}")
	@ApiOperation(value = "Update employee record with given Employee ID", notes = "Update the employee", response = Employee.class)
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
			@Valid @RequestBody EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		EmployeeDTO dto = employeeService.updateEmployee(employeeId, employeeDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);

	}

}
