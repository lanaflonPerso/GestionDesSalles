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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenor.tsf.GestionDesSalles.entity.Department;
import com.tenor.tsf.GestionDesSalles.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	
	
	@Test
	public void testGetAllUser() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/User")).andExpect(status().isOk());
		resultActions.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Department dept = new Department();
		dept.setId(1l);
		User user = new User();
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setPassword("HelloPassword");
		user.setDepartment(dept);

		ResultActions resultActions = mockMvc.perform(
				post("/User").contentType("application/json").content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated());
		resultActions.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		User user = new User();
		user.setId(1L);
		ResultActions resultActions = mockMvc.perform(
				post("/User/delete").contentType("application/json").content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isNotFound());

		resultActions.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {

		User user = new User();
		user.setLastName("lastName");

		ResultActions resultActions = mockMvc.perform(put("/User").contentType("application/json")
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
		resultActions.andDo(print());
		
	}

	/*
	 * case KO
	 */

	@Test
	public void testCreateCaseException() throws JsonProcessingException, Exception {
		User usr = new User();

		ResultActions resultActions = mockMvc.perform(
				post("/User/create").contentType("application/json").content(objectMapper.writeValueAsString(usr)))
				.andExpect(status().isNotFound());
		resultActions.andDo(print());
	}

	@Test
	public void testUpdateException() throws JsonProcessingException, Exception {
		User user = new User();

		ResultActions rs = mockMvc.perform(
				put("/User/update").contentType("application/json").content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isNotFound());
		rs.andDo(print());
	}

	@Test
	public void testDeleteException() throws Exception {
		ResultActions resultActions = mockMvc.perform(delete("/User/delete/132456")).andExpect(status().isNotFound());
		resultActions.andDo(print());
	}
}
