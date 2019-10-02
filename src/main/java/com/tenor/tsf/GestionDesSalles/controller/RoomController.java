package com.tenor.tsf.GestionDesSalles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenor.tsf.GestionDesSalles.entity.Room;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.service.RoomService;


@RestController
@RequestMapping(value="/Room")
public class RoomController {
	
	@Autowired
	RoomService service;
	
	@GetMapping
	public ResponseEntity<List<Room>>getAllSalle(){
		if (service.AllRooms().isEmpty()) {
			return new ResponseEntity<List<Room>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Room>>(service.AllRooms(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Room> create(@RequestBody Room room) {
		service.createNewRoom(room);
		return new ResponseEntity<Room>(HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<Room> delete(@PathVariable Room room){
		service.deleteRoom(room);
		return new ResponseEntity<Room>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Room> update(@RequestBody Room room) throws GSExeptionNotFound{
		service.updateRoomInformations(room);
		return new ResponseEntity<Room>(HttpStatus.OK);
	} 
}
