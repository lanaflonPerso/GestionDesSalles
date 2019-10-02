package com.tenor.tsf.GestionDesSalles.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.tenor.tsf.GestionDesSalles.entity.Department;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionAlreadyExist;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Log4j2
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTest {
	
	/*
	 * Department service testing class 
	 */
	
	@Autowired
	DepartmentService service;

	/*
	 * Testing
	 */
	@Test
	public void helloWorld() {
		log.info("Hello world !!");
	}
	
	/*
	 * Case OK
	 */

	@Test
	@Order(1)
	public void testCreateNewDepartement() throws GSExeptionAlreadyExist {
		Department dept1 = new Department();
		dept1.setLabel("dev");
		service.createNewDepartement(dept1);
		log.info(dept1);		
		assertNotNull(dept1);
		
		Department dept2 = new Department();
		dept2.setLabel("Design");
		service.createNewDepartement(dept2);
		log.info(dept2);
		assertNotNull(dept2);
		
		Department dept3 = new Department();
		dept3.setLabel("Reseau");
		service.createNewDepartement(dept3);
		log.info(dept3);
		assertNotNull(dept3);
				
	}

	@Test
	@Order(2)
	public void testUpdateDepartment() throws GSExeptionAlreadyExist, GSExeptionNotFound {
		Department dept = new Department();
		dept.setId(1L);
		dept.setLabel("development");
		service.updateDepartment(dept);
		log.info(dept);
	}

	@Test
	@Order(3)
	public void testDeleteDepartment() throws GSExeptionNotFound {
		Department dept = new Department();
		dept.setId(2L);
		assertTrue(service.deleteDepartment(dept));
	}
	
	/*
	 * Case KO
	 */
	
	@Test(expected=NullPointerException.class)
	public void testCreateNewDepartementCaseNull() throws GSExeptionAlreadyExist {
		Department dept = new Department();	
		service.createNewDepartement(dept);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateNewDepartementCaseEmpty() throws GSExeptionAlreadyExist {
		Department dept1 = new Department();
		dept1.setLabel("");
		service.createNewDepartement(dept1);
		log.info(dept1);		
		assertNotNull(dept1);		
	}
	
	@Test(expected=GSExeptionAlreadyExist.class)
	public void testCreateNewDepartementCaseAlreadyExist() throws GSExeptionAlreadyExist {
		Department dept1 = new Department();
		dept1.setLabel("dev");
		service.createNewDepartement(dept1);
		log.info(dept1);		
		assertNotNull(dept1);		
		
		Department dept2 = new Department();
		dept2.setLabel("dev");
		service.createNewDepartement(dept2);
		log.info(dept2);		
		assertNotNull(dept2);	
	}
	
	@Test(expected=GSExeptionNotFound.class)
	public void testUpdateDepartmentCaseNotFound() throws GSExeptionAlreadyExist, GSExeptionNotFound {
		Department dept = new Department();
		dept.setId(9L);
		dept.setLabel("dev");
		service.updateDepartment(dept);
		log.info(dept);
	}
	
	
	@Test(expected=GSExeptionNotFound.class)
	public void testDeleteDepartmentCaseNotFound() throws GSExeptionNotFound {
		Department dept = new Department();
		dept.setId(22L);
		assertTrue(service.deleteDepartment(dept));
	}
	

}
