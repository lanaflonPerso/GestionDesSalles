package com.tenor.tsf.GestionDesSalles.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.GestionDesSalles.entity.Department;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	/*
	 * test department controller case OK
	 */
	
	@Test
	public void testGetAllDepartments() throws Exception {
		ResultActions rs = mockMvc.perform(get("/Department")).andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Department dept = new Department();
		dept.setLabel("Dev");

		ResultActions resultActions = mockMvc.perform(post("/Department/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isCreated());
		resultActions.andDo(print());

		Department dept2 = new Department();
		dept2.setLabel("Reseau");

		ResultActions resultActions2 = mockMvc.perform(post("/Department/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept2))).andExpect(status().isCreated());
		resultActions2.andDo(print());
		
		Department dept3 = new Department();
		dept3.setLabel("Securité");

		ResultActions resultActions3 = mockMvc.perform(post("/Department/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept3))).andExpect(status().isCreated());
		resultActions3.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Department dept = new Department();
		dept.setId(1L);
		dept.setLabel("Devlopment");
		ResultActions rs = mockMvc.perform(put("/Department/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isOk());
		rs.andDo(print());
	}	

	@Test
	public void testDelete() throws Exception {
	
		Department dept = new Department();
		dept.setId(2L);
		
		ResultActions resultActions = ((ResultActions) ((MockHttpServletRequestBuilder) mockMvc.perform( delete("/Department/delete/")))
				.content(objectMapper.writeValueAsString(dept)))
				.andExpect(status().isOk());
		resultActions.andDo(print());
	}
	
	/*
	 * testdepartment contoller case KO
	 */
	
	@Test
	public void testCreateCaseException() throws JsonProcessingException, Exception {
		Department dept = new Department();

		ResultActions resultActions = mockMvc.perform(post("/Departement/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isNotImplemented());
		resultActions.andDo(print());
	}
	
	@Test
	public void testDeleteCaseException() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Department/delete/123"))
				.andExpect(status().isNotFound());
		rs.andDo(print());}

	
	@Test
	public void testUpdateCaseException() throws JsonProcessingException, Exception {
		Department dept = new Department();
		dept.setId(95L);
		dept.setLabel("Devlopment");
		ResultActions rs = mockMvc.perform(put("/Department/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(dept))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
