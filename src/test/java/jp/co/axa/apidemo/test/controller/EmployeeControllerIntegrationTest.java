package jp.co.axa.apidemo.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.controller.EmployeeController;
import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.service.EmployeeService;
import jp.co.axa.apidemo.test.util.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerIntegrationTest {

	@MockBean
	private EmployeeService employeeService;

	@InjectMocks
	EmployeeController employee;

	@Autowired
	ObjectMapper objectmapper;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	/*
	 * Integration test for create employee 
	 */
	@Test
	public void testCreateEmployee35_whenValidInput_thenReturnsSuccess() throws Exception {
		// given
		EmployeeDTO dto = BaseTest.buildEmployeeDTO();
		when(employeeService.createEmployee(any())).thenReturn(dto);
		// when
		mockMvc.perform(post("/api/v1/employees").contentType(MediaType.APPLICATION_JSON)
				.content(objectmapper.writeValueAsString(dto)))
				// then
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.name", is(dto.getName())))
				.andExpect(jsonPath("$.department", is(dto.getDepartment())))
				.andExpect(jsonPath("$.salary", is(dto.getSalary())));
	}
	/*
	 * Integration test to fetch employee by id.
	 */
	@Test
	public void testGetEmployeeById() throws Exception {
		// given
		EmployeeDTO dto = BaseTest.buildEmployeeDTO();

		when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.of(dto));

		// when
		mockMvc.perform(get("/api/v1/employees/1"))
				// then
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(dto.getId().intValue())))
				.andExpect(jsonPath("$.name", is(dto.getName())))
				.andExpect(jsonPath("$.department", is(dto.getDepartment())))
				.andExpect(jsonPath("$.salary", is(dto.getSalary())));
	}

	/*
	 * Integration test to test delete employee functionality. 
	 */
	@Test
	public void testDeleteEmployee() throws Exception {
		// given
		doNothing().when(employeeService).deleteEmployee(anyLong());
		// when
		mockMvc.perform(delete("/api/v1/employees/1"))
				// then
				.andExpect(status().is2xxSuccessful());
	}

	/*
	 * Integration test to test retrieve all employees.
	 */
	@Test
	public void testGetAllEmployees() throws Exception {
		// given
		EmployeeDTO dto = BaseTest.buildEmployeeDTO();
		List<EmployeeDTO> list = new ArrayList<>(Arrays.asList(dto));
		when(employeeService.retrieveEmployees()).thenReturn(list);
		// when
		mockMvc.perform(get("/api/v1/employees"))
				// then
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(list.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].name", is(list.get(0).getName())))
				.andExpect(jsonPath("$[0].salary", is(list.get(0).getSalary())))
				.andExpect(jsonPath("$[0].department", is(list.get(0).getDepartment())));
	}

	/*
	 * Integration test to test update employee.
	 */
	@Test
	public void testUpdateEmployee_whenValidInput_thenReturnsSuccess() throws Exception {
		// given
		EmployeeDTO dto = BaseTest.buildEmployeeDTO();
		when(employeeService.updateEmployee(anyLong(), any())).thenReturn(dto);
		// when
		mockMvc.perform(put("/api/v1/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectmapper.writeValueAsString(dto)))
				// then
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.id", is(dto.getId().intValue())))
				.andExpect(jsonPath("$.name", is(dto.getName())))
				.andExpect(jsonPath("$.department", is(dto.getDepartment())))
				.andExpect(jsonPath("$.salary", is(dto.getSalary())));
	}
}
