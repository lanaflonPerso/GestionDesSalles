package com.tenor.tsf.GestionDesSalles.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.Room;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.repository.RoomRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RoomService {
	
	@Autowired
	RoomRepository repo;
	
	public List<Room> AllRooms(){
		return (List<Room>) repo.findAll();
	}
	
	public Room getRoomById(Long id) throws GSExeptionNotFound {

		Room room = null;
		Boolean check = false;

		for (Room rooms : repo.findAll()) {
			if (rooms.getId() == id) {
				room = rooms;
				check = true;
			}
		}
		if(check==true){
			return room;
		}else{	
			throw new GSExeptionNotFound("Room with id:"+id);
		}
		
	}
	
	/*
	 *  Method to create a room
	 *  @return room object created
	 */
	
	public Room createNewRoom(Room room){
		Validate.notEmpty(room.getLabel(),"Enter a value in label field.");
		Validate.notNull(room.getCapacite(),"Please enter room capacite");
		
		log.info("Room "+room.getLabel()+" created");		
		return repo.save(room);		
	}
	
	
	public Boolean updateRoomInformations(Room room) throws GSExeptionNotFound{
		Validate.notNull(room.getId(),"Enter valide room id !");
		Validate.notNull(getRoomById(room.getId()),"The ID entred is not found");
		
		if (repo.save(room) != null){
			log.info("Room info updated ");
			return true;
		}else
			return false;
	}
	
	public Boolean deleteRoom(Room room){
		Validate.notNull(room.getId(),"Enter valide room id !");
		Validate.notNull(repo.findById(room.getId()),"The ID entred is not found");
		
		repo.delete(room);
		
		if(repo.findById(room.getId()) != null){
			return false;
		}else{
			return true;
		}
	}
	
	

}
