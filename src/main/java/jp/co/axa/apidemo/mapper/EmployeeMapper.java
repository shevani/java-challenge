package jp.co.axa.apidemo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entity.Employee;

/**
 * Mapper class for DTO object to Employee Object and vice versa
 *
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	Employee toEmployee(EmployeeDTO employeeDto);

	EmployeeDTO toEmployeeDTO(Employee employee);

	List<EmployeeDTO> toEmployeeDTOs(List<Employee> employees);

	List<Employee> toEmployees(List<EmployeeDTO> employeeDTOs);

}
