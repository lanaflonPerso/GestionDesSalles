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
import com.tenor.tsf.GestionDesSalles.entity.Room;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RoomControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	/*
	 * test room controller case OK
	 */
	
	@Test
	public void testGetAllRooms() throws Exception {
		ResultActions rs = mockMvc.perform(get("/Room")).andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Room room = new Room();
		room.setLabel("Assila");
		room.setCapacite(20L);

		ResultActions resultActions = mockMvc.perform(post("/Room/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(room))).andExpect(status().isCreated());
		resultActions.andDo(print());

		Room room2 = new Room();
		room2.setLabel("Casa");
		room2.setCapacite(200L);

		ResultActions resultActions2 = mockMvc.perform(post("/Room/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(room2))).andExpect(status().isCreated());
		resultActions2.andDo(print());
		
		Room room3 = new Room();
		room3.setLabel("Assila");
		room3.setCapacite(85L);

		ResultActions resultActions3 = mockMvc.perform(post("/Room/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(room3))).andExpect(status().isCreated());
		resultActions3.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Room room = new Room();
		room.setId(2L);
		room.setLabel("Casablanca");
		room.setCapacite(80L);
		ResultActions rs = mockMvc.perform(put("/Room/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(room))).andExpect(status().isOk());
		rs.andDo(print());
	}	

	@Test
	public void testDelete() throws Exception {
	
		Room room = new Room();
		room.setId(3L);
		
		ResultActions resultActions = ((ResultActions) ((MockHttpServletRequestBuilder) mockMvc.perform( delete("/Room/delete/")))
				.content(objectMapper.writeValueAsString(room)))
				.andExpect(status().isOk());
		resultActions.andDo(print());
	}
	
	/*
	 * testt room contoller case KO
	 */
	
	@Test
	public void testCreateCaseException() throws JsonProcessingException, Exception {
		Room room = new Room();

		ResultActions resultActions = mockMvc.perform(post("/Room/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(room))).andExpect(status().isNotImplemented());
		resultActions.andDo(print());
	}
	
	@Test
	public void testDeleteCaseException() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Room/delete/123"))
				.andExpect(status().isNotFound());
		rs.andDo(print());}

	
	@Test
	public void testUpdateCaseException() throws JsonProcessingException, Exception {
		Room room = new Room();
		room.setId(95L);
		room.setLabel("Room");
		ResultActions rs = mockMvc.perform(put("/Room/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(room))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
