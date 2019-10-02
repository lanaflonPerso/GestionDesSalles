package com.tenor.tsf.GestionDesSalles.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.User;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionIsNull;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService {

	/*
	 * @UserRepository is repository of User class
	 */

	@Autowired
	UserRepository repo;

	public List<User> getAllUsers(){
		return (List<User>) repo.findAll();
		
	}
	
	public Optional<User> findUserById(Long id){
		
		return repo.findById(id);
	}
	
	
	public User getUserById(Long id) throws GSExeptionNotFound {

		User user = null;

		for (User users : repo.findAll()) {
			if (users.getId() == id) {
				user = users;
			}
		}
		
		if(user == null){
			throw new GSExeptionNotFound("User with id : "+id);
		}
		return user;
	}

	/*
	 * Method to check if User exist return check true if user exist and false
	 * if not
	 */

	public Boolean checkIfUserExist(User user) {
		Validate.notNull(user.getId(), "Enter valide id of user");
		Boolean check = false;

		return check;
	}

	/*
	 * Method to create new user
	 */

	public User createUser(User user) {

		Validate.notBlank(user.getFirstName(), "Enter your first name");
		Validate.notBlank(user.getLastName(), "Enter your last name");
		Validate.notBlank(user.getPassword(), "Enter your password");

		/**
		 * Generation login from assembling first char of first name and last
		 * name
		 **/
		String login = user.getFirstName().charAt(0) + user.getLastName();
		user.setLogin(login);

		log.info("User created with login : " + login);
		return repo.save(user);
	}

	public Boolean updateUserInformations(User user) throws GSExeptionNotFound {
		Validate.notNull(user.getId(), "Enter valide id of user");
//		Validate.notBlank(user.getFirstName(), "Enter your first name");
//		Validate.notBlank(user.getLastName(), "Enter your last name");
//		Validate.notBlank(user.getPassword(), "Enter your password");
		Validate.notNull(repo.findById(user.getId()), "The ID entred is not found");

		Boolean check = false;
		
		for(User users : repo.findAll()){
			if(users.getId() == user.getId()){
				log.info("User updated");
				repo.save(user);
				check = true;
			}
		}
		
		if(check==false){
			throw new GSExeptionNotFound("User with id: " + user.getId());
		}else{
			return true;
		}	
	}

	public Boolean deleteUser(User user) throws GSExeptionNotFound {
		Validate.notNull(user.getId(), "Enter valide id of user");
		Validate.notNull(repo.findById(user.getId()), "The ID entred is not found");
		
		user = getUserById(user.getId());			
		if(user==null){
			throw new GSExeptionNotFound("User with id : "+user.getId());
		}else{
			repo.delete(user);
		}
		

		if (repo.findById(user.getId()) != null) {
			return false;
		} else {
			return true;

		}
	}
}
