package com.tenor.tsf.GestionDesSalles.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenor.tsf.GestionDesSalles.entity.User;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.service.UserService;


@RestController
@RequestMapping(value="/User")
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>>getAllUser(){
		if (service.getAllUsers().isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(service.getAllUsers(),HttpStatus.OK);
	}
	
	@PostMapping//(value = "/create")
	public ResponseEntity<User> create(@RequestBody User user)  {
		service.createUser(user);
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	@DeleteMapping//(value = "/delete")
	public ResponseEntity<User> delete(@RequestBody User user) throws GSExeptionNotFound  {
		service.deleteUser(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PutMapping//(value = "/update")
	public ResponseEntity<Void> update(@RequestBody User user) throws GSExeptionNotFound {
		Optional<User> optionalUser = service.findUserById(user.getId());
		
		/*
		 * TO DO 
		 * get user by id and affect him new value before insert him into DB
		 */
		
		if (!service.findUserById(user.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		System.out.println("+++"+optionalUser);

		service.updateUserInformations(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	} 
}
