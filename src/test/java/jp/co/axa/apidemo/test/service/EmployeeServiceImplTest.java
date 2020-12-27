package jp.co.axa.apidemo.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.mapper.EmployeeMapper;
import jp.co.axa.apidemo.repository.EmployeeRepository;
import jp.co.axa.apidemo.service.EmployeeServiceImpl;
import jp.co.axa.apidemo.test.util.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	/**
	 * Test retrieve all Employees method and verify findAll is invoked
	 * @throws Exception
	 */
	@Test
	public void shouldReturnAllEmployees() throws Exception {
		Employee entity = BaseTest.buildEmployeeEntity();
		List<Employee> list = new ArrayList<>(Arrays.asList(entity));
		when(employeeRepository.findAll()).thenReturn(list);
		List<EmployeeDTO> empList = employeeService.retrieveEmployees();
		assertThat(empList.size() > 0);
		assertThat(empList.get(0).getName().contentEquals("Mark"));

		verify(this.employeeRepository, times(1)).findAll();

	}
    
	/**
	 * Test find Employee by ID method  and verify findById is invoked
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void shouldReturnEmployeeById() throws ResourceNotFoundException {
		// given
		Employee entity = BaseTest.buildEmployeeEntity();
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(entity));

		// when
		Optional<EmployeeDTO> dto = employeeService.getEmployeeById(1L);

		// then

		assertTrue(dto.isPresent());
		verify(employeeRepository, times(1)).findById(1L);

	}

	/**
	 * Test create new Employee method and verify save method is invoked
	 * @throws Exception
	 */
	@Test
	public void shouldCreateEmployee() throws Exception {
		// given
		Employee entity = BaseTest.buildEmployeeEntity();
		when(employeeRepository.save(entity)).thenReturn(entity);

		// when
		EmployeeDTO dto = employeeService.createEmployee(EmployeeMapper.INSTANCE.toEmployeeDTO(entity));

		// then
		assertNotNull(dto);
		verify(employeeRepository, times(1)).save(entity);
	}

	/**
	 * Test update Employee method with given Employee Id and verify save method is invoked
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void shouldUpdateEmployee() throws ResourceNotFoundException {
		// given
		Employee entity = BaseTest.buildEmployeeEntity();
		when(employeeRepository.save(entity)).thenReturn(entity);

		// when
		EmployeeDTO dto = employeeService.updateEmployee(1L, EmployeeMapper.INSTANCE.toEmployeeDTO(entity));

		// then
		assertNotNull(dto);
		verify(employeeRepository, times(1)).save(entity);
	}
	
	/**
	 * Test delete Employee with given Id and verify deleteById is invoked
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void shouldDeleteEmployee() throws ResourceNotFoundException {
		Employee entity = BaseTest.buildEmployeeEntity();
		when(employeeRepository.findById(1l)).thenReturn(Optional.of(entity));
		employeeService.deleteEmployee(entity.getId());
		verify(employeeRepository, times(1)).deleteById(1l);

	}

}
