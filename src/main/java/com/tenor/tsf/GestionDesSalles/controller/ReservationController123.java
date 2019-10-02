//package com.tenor.tsf.GestionDesSalles.controller;
//
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tenor.tsf.GestionDesSalles.entity.Reservation;
//import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionAlreadyExist;
//import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionIsNull;
//import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
//import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionReservation;
//import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionRoomIsReserved;
//import com.tenor.tsf.GestionDesSalles.service.ReservationService123;
//
//
//@RestController
//@RequestMapping(value="/Reservation")
//public class ReservationController123 {
//	@Autowired
//	ReservationService123 service;
//	
//	@GetMapping
//	public ResponseEntity<List<Reservation>>getAllReservation(){
//		if (service.getAllReservation().isEmpty()) {
//			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<List<Reservation>>(service.getAllReservation(),HttpStatus.OK);
//	}
//	@PostMapping(value = "/create")
//	public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) throws GSExeptionAlreadyExist, GSExeptionReservation, GSExeptionRoomIsReserved  {
//		service.create(reservation);
//		return new ResponseEntity<Reservation>(HttpStatus.CREATED);
//
//	}
//	@DeleteMapping(value = "delete/{id}")
//	public ResponseEntity<Reservation> delete(@PathVariable Long id) throws GSExeptionNotFound {
//		service.delete(id);
//		return new ResponseEntity<Reservation>(HttpStatus.OK);
//	}
//	@PutMapping(value = "/update")
//	public ResponseEntity<Reservation> update(@RequestBody Reservation reservation) throws GSExeptionIsNull, GSExeptionNotFound {
//		service.update(reservation);
//		return new ResponseEntity<Reservation>(HttpStatus.OK);
//	} 
//}
