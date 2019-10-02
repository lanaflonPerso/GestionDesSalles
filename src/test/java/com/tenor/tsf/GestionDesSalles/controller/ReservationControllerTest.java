package com.tenor.tsf.GestionDesSalles.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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
import com.tenor.tsf.GestionDesSalles.entity.Reservation;
import com.tenor.tsf.GestionDesSalles.entity.Room;
import com.tenor.tsf.GestionDesSalles.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	/*
	 * Unit test for reservation class
	 * case OK 
	 */


	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User user = new User();
		user.setId(3l);
		Room room = new Room();
		room.setId(21l);

		LocalDateTime startDate = LocalDateTime.of(2019, 11, 15, 10, 00);
		LocalDateTime endDate = LocalDateTime.of(2019, 11, 15, 16, 00);

		res.setStartDate(startDate);
		res.setEndDate(endDate);
		res.setRoom(room);
		res.setUser(user);
		ResultActions rs = mockMvc.perform(post("/Reservation/").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isCreated());
		rs.andDo(print());
	}

	@Test
	public void testDelete() throws Exception {
		ResultActions rs = mockMvc.perform( delete("/Reservation/delete/7"))
				.andExpect(status().isOk());
		rs.andDo(print());
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		Reservation reservation = new Reservation();
		User user = new User();
		user.setId(3l);
		Room room = new Room();
		room.setId(9l);

		LocalDateTime startDate = LocalDateTime.of(2019, 11, 15, 10, 00);
		LocalDateTime endDate = LocalDateTime.of(2019, 11, 15, 16, 00);
		reservation.setId(7l);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setRoom(room);
		reservation.setUser(user);
		ResultActions rs = mockMvc.perform(put("/Reservation/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(reservation))).andExpect(status().isOk());
		rs.andDo(print());
	}
	
	/*
	 * case KO
	 */
	
	@Test
	public void testCreateCaseKo() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		ResultActions rs = mockMvc.perform(post("/Reservation/create").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isNotImplemented());
		rs.andDo(print());
	}
	
	@Test
	public void testUpdateCaseKo() throws JsonProcessingException, Exception {
		Reservation res = new Reservation();
		User usr = new User();
		usr.setId(3l);
		Room sl = new Room();
		sl.setId(9l);

		LocalDateTime datedebut = LocalDateTime.of(2019, 11, 15, 10, 00);
		LocalDateTime datefin = LocalDateTime.of(2019, 11, 15, 16, 00);
		res.setId(65117l);
		res.setStartDate(datedebut);
		res.setEndDate(datefin);
		res.setRoom(sl);
		res.setUser(usr);
		ResultActions rs = mockMvc.perform(put("/Reservation/update").contentType("application/json")
				.content(objectMapper.writeValueAsString(res))).andExpect(status().isNotFound());
		rs.andDo(print());
	}

}
