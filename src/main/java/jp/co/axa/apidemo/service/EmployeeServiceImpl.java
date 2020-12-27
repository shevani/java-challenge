package jp.co.axa.apidemo.service;


import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.mapper.EmployeeMapper;
import jp.co.axa.apidemo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Get all employees
	 * @return List of Employees
	 */
	@Cacheable(cacheNames = "employeesCache")
	public List<EmployeeDTO> retrieveEmployees() {
		List<Employee> employee = employeeRepository.findAll();
		logger.info("Retrieve employees from Database");
		return employee.stream().map(e -> EmployeeMapper.INSTANCE.toEmployeeDTO(e)).collect(Collectors.toList());
	}
	/**
	 * Get Employee By Id.
	 * @param long Employee_id
	 * @return Employee
	 */
	@Cacheable(cacheNames = "employeesCache", key = "#p0")
	public Optional<EmployeeDTO> getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Optional<Employee> employee= employeeRepository.findById(employeeId);
		if(employee.isPresent())
		{
			Employee emp = employee.get();
			return Optional.of(EmployeeMapper.INSTANCE.toEmployeeDTO(emp));
		}
		logger.info("Retrieve employee from Database"+employeeId);
		throw new ResourceNotFoundException("Employee not found for ID"+employeeId);
	}

	/**
	 * Create or Employee
	 * @param employee Employee to create
	 * @return Created Employee
	 */

	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeDTO);
		logger.info("Employee created in Database");
        return EmployeeMapper.INSTANCE.toEmployeeDTO(employeeRepository.save(employee));
	}

	/**
	 * Delete Employee by Id.
	 * @param employee_id Employee_Id
	 */
	@CacheEvict(cacheNames = "employeesCache",allEntries=true, key = "#p0")
	public void deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		logger.info("Employee Deleted");
		employeeRepository.deleteById(employeeId);

	}

	/**
	 * Update Employee.
	 * @param employee_id Employee_Id
	 * @param employee  employeeDetails
	 * @return Updated Employee
	 */
	@CachePut(cacheNames = "employeesCache", key = "#p0")
	public EmployeeDTO updateEmployee(long employeeId, EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		//Employee employee = employeeRepository.findById(employeeId)
		//		.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		Employee employeeToUpdate = EmployeeMapper.INSTANCE.toEmployee(employeeDetails);
		employeeToUpdate.setId(employeeId);
		logger.info("Employee updated in database");
		return EmployeeMapper.INSTANCE.toEmployeeDTO(employeeRepository.save(employeeToUpdate));

	}
}