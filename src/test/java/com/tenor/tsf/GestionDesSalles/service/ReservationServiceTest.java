package com.tenor.tsf.GestionDesSalles.service;


import java.time.LocalDateTime;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.GestionDesSalles.entity.Reservation;
import com.tenor.tsf.GestionDesSalles.entity.Room;
import com.tenor.tsf.GestionDesSalles.entity.User;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionIsNull;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionReservation;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionRoomIsReserved;

import lombok.extern.log4j.Log4j2;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ReservationServiceTest {
	

	@Autowired
	ReservationService service;
	@Autowired
	RoomService roomService;
	@Autowired 
	UserService userService;
	
	@Test
	public void test() {
		System.out.println("Hello world ");
	}

	/*
	 * Method create reservation of room
	 * 
	 */
	@Test
	@Ignore
	public void testCreate() throws GSExeptionReservation, GSExeptionRoomIsReserved, GSExeptionNotFound, GSExeptionIsNull  {
		Reservation reservation = new Reservation();
		
		Room room = new Room();
		room = roomService.getRoomById(1L);
		
		User user = new User();
		user = userService.getUserById(1L);

		
		reservation.setRoom(room);
		reservation.setStartDate(LocalDateTime.of(2019, 10, 8, 7, 30));
		reservation.setEndDate(LocalDateTime.of(2019, 10, 8, 9, 30));
		reservation.setUser(user);
		
//		log.info(reservation);
		reservation=service.addReservation(reservation);
		log.info("Created reservation : "+reservation);
		
	}


}
