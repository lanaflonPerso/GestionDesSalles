package com.tenor.tsf.GestionDesSalles.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.GestionDesSalles.entity.Department;
import com.tenor.tsf.GestionDesSalles.entity.User;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionAlreadyExist;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

	/*
	 * User service test class
	 */
	
	@Autowired
	private UserService service;
	
	@Test
	@Ignore
	public void test() {
		log.info("Hello World !");
	}
	
	/*
	 * Test  User case OK
	 */	
	
	@Test
	@Ignore
	public void createUser() throws GSExeptionAlreadyExist{
		
		Department dept1 = new Department();
		dept1.setId(1L);
		
		User user = new User();
		user.setFirstName("Mohamed");
		user.setLastName("Makrouuuuuuuuuuuum");
		user.setPassword("qwerty");
		user.setDepartment(dept1);
		User result = service.createUser(user);
		
		log.info(user);
		assertEquals(user,result);
		
		User user2 = new User();
		user2.setFirstName("Ilyas");
		user2.setLastName("Makroum");
		user2.setPassword("pass");
		user2.setDepartment(dept1);
		User result2 = service.createUser(user2);
		
		log.info(user2);
		assertEquals(user2,result2);
		
	}
	
	@Test
	public void updateUser() throws GSExeptionAlreadyExist, GSExeptionNotFound{
		
		
		User user = new User();
		
		user = service.getUserById(1L);		
		user.setLastName("Makroum");
		user.setPassword("azerty");
		Boolean result = service.updateUserInformations(user);
		
		log.info(user);
		assertTrue(result);
	}
	
	@Test
	public void deteleUser() throws GSExeptionAlreadyExist, GSExeptionNotFound{
		
		User user = new User();
		user.setId(2L);
		Boolean result = service.deleteUser(user);

		assertFalse(result);
		log.info("Users after delete"+service.getAllUsers());
		
	}

	/*
	 * Test  User case KO
	 */	
	
	@Test(expected=NullPointerException.class)
	public void createUserCaseNull(){
	
		User user = new User();
		User result = service.createUser(user);
		
		log.info(user);
		assertEquals(user,result);
		
		
	}
	
	@Test(expected=GSExeptionNotFound.class)
	public void updateUserCaseDontExist() throws GSExeptionNotFound{
		
		
		User user = new User();
		
		user = service.getUserById(15L);		
		user.setLastName("Makroum");
		user.setPassword("azerty");
		Boolean result = service.updateUserInformations(user);
		
		log.info(user);
		assertTrue(result);
	}
	
	@Test(expected=GSExeptionNotFound.class)
	public void deteleUserCaseNotFound() throws GSExeptionNotFound {
		
		User user = new User();
		user.setId(10L);
		Boolean result = service.deleteUser(user);

		assertFalse(result);
		log.info("Users after delete"+service.getAllUsers());
		
	}

	
	
	
	
}
