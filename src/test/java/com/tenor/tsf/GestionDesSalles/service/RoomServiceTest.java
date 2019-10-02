package com.tenor.tsf.GestionDesSalles.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.GestionDesSalles.entity.Room;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;

import lombok.extern.log4j.Log4j2;
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
@Transactional
public class RoomServiceTest {
	
	@Autowired
	private RoomService service;

	@Test
	public void testCreateNewRoom() {
		Room room = new Room();
		room.setLabel("Assila");
		room.setCapacite(40L);
		Room roomResult = service.createNewRoom(room);
		log.info(roomResult);
		assertEquals(roomResult, room);

		Room room2 = new Room();
		room2.setLabel("Tanger");
		room2.setCapacite(20L);
		Room room2Result = service.createNewRoom(room2);
		log.info(room2Result);
		assertEquals(room2Result, room2);

		Room room3 = new Room();
		room3.setLabel("Casa");
		room3.setCapacite(100L);
		Room room3Result = service.createNewRoom(room3);
		log.info(room3Result);
		assertEquals(room3Result, room3);

		Room room4 = new Room();
		room4.setLabel("Laarache");
		room4.setCapacite(10L);
		Room room4Result = service.createNewRoom(room4);
		log.info(room4Result);
		assertEquals(room4Result, room4);
		
		log.info("BD after create room : "+service.AllRooms());
		
	}

	@Test
	public void testUpdateRoomInformations() throws GSExeptionNotFound {
		
		Room room = new Room();
		room = service.getRoomById(1L);
		room.setCapacite(20L);
		Boolean result = service.updateRoomInformations(room);
		assertTrue(result);
		
		Room room2 = new Room();
		room2 = service.getRoomById(3L);
		
		System.out.println(">>>>>>>>>>>>>>>>>> "+room2);
		room2.setLabel("Casablanca");
		Boolean result2 = service.updateRoomInformations(room2);
		assertTrue(result2);
	
		log.info("DB after update"+service.AllRooms());
	}

	@Test
	public void testDeleteRoom() {
		
		Room room = new Room();
		room.setId(2L);
		Boolean result = service.deleteRoom(room);
		
		assertFalse(result);
		log.info("DB after delete"+service.AllRooms());

	}
	
	/*
	 * 
	 * 
	 * 
	 * Case KO
	 * 
	 * 
	 * 
	 * 
	 */
	
	@Test(expected=NullPointerException.class)
	public void testCreateNewRoomCaseNull() {
		Room room = new Room();
		Room roomResult = service.createNewRoom(room);
		log.info(roomResult);
		assertEquals(roomResult, room);		
	}

	@Test(expected=GSExeptionNotFound.class)
	public void testUpdateRoomInformationsCaseNotFound() throws GSExeptionNotFound {
		
		Room room = new Room();
		room = service.getRoomById(33L);
		Boolean result = service.updateRoomInformations(room);
		assertTrue(result);
		
	}

	@Test(expected=GSExeptionNotFound.class)
	public void testDeleteRoomNotFound() throws GSExeptionNotFound {
		
		Room room = new Room();
		room = service.getRoomById(33L);
		Boolean result = service.deleteRoom(room);
		
		assertFalse(result);
		log.info("DB after delete"+service.AllRooms());

	}
	

	
	
}
