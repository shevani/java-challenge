package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	
	/**
     * Get All Employees.
     * 
     * @return List of all employees.
     */
	
    public List<EmployeeDTO> retrieveEmployees();

    
    /**
     * Get Employee By Employee_Id.
     * 
     * @param employee_id Employee_Id
     * @return Employee
     */
    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) throws ResourceNotFoundException;

    /**
     * Create Employee record
     * @param employee Employee to create
     * @return Saved Employee
     */
    public  EmployeeDTO createEmployee(EmployeeDTO employee);
    
    /**
     * Delete Employee by Employee_Id.
     * @param employee_id Employee_Id
     */
    public void deleteEmployee(Long employeeId) throws ResourceNotFoundException;

    
    /**
     * Update Employee by Employee_Id.
     * @param employee_id Employee_Id,employeeDTO employeeDetails
     */
    public EmployeeDTO updateEmployee(long employeeId,EmployeeDTO employeeDetails) throws ResourceNotFoundException;
}