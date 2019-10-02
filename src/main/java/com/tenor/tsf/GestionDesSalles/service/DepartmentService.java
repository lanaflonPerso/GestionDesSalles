package com.tenor.tsf.GestionDesSalles.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.Validate;

import com.tenor.tsf.GestionDesSalles.entity.Department;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionAlreadyExist;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionIsNull;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.repository.DepartmentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class DepartmentService {

	/*
	 * Department Service
	 * 
	 * @DepartmentRepository is repository of department class
	 */

	@Autowired
	DepartmentRepository repo;

	/*
	 * Method to check if value entered is not null or not empty return true if
	 * value entered is not null or not empty return false if value entered is
	 * null or empty
	 */
	public List<Department> getAllDepartment(){
		return (List<Department>) repo.findAll();
	}
	
	public Boolean checkIfLabelNotNull(Department dept) throws GSExeptionIsNull {
		Validate.notNull(dept.getLabel(), "Please enter a value");
		Boolean check = true;

		if (dept.getLabel() == "") {
			check = false;
			throw new GSExeptionIsNull("Department label");
		}

		return check;

	}

	/*
	 * Method to create new department
	 */

	public Department createNewDepartement(Department dept) throws GSExeptionAlreadyExist {
		// Validate.isTrue(checkIfLabelNotNull(dept), "Please enter a value");

		Validate.notEmpty(dept.getLabel(), "Label can't be empty");
		Boolean check = false;

		for (Department depts : repo.findAll()) {
			if (depts.getLabel() == dept.getLabel()) {
				check = true;
			}
		}

		if (check == true) {
			throw new GSExeptionAlreadyExist(dept.getLabel());
		}else{
			log.info("Department " + dept.getLabel() + " is created");
			return repo.save(dept);
		}
	}

	public void updateDepartment(Department dept) throws GSExeptionAlreadyExist, GSExeptionNotFound {
		Validate.notNull(dept.getId(), "Id can't be null");
		Validate.notEmpty(dept.getLabel(), "Label can't be empty");

		// Validate.isTrue(checkIfLabelNotNull(dept), "Please enter a value");
		Boolean check = false;

		for (Department depts : repo.findAll()) {
			if (depts.getId() == dept.getId()) {
				check = true;
			}
		}

		if (check == true) {
			log.info("Department is updated");
			createNewDepartement(dept);
		} else {
			throw new GSExeptionNotFound("Department with id: " + dept.getId());
		}
	}

	public Boolean deleteDepartment(Department dept) throws GSExeptionNotFound {
		Validate.notNull(dept.getId(), "Id can't be null");
		Boolean check = false;

		for (Department depts : repo.findAll()) {
			if (depts.getId() == dept.getId()) {
				check = true;
			}
		}

		if (check == true) {
			log.info("Department " + dept.getLabel() + " is deleted");
			repo.delete(dept);
			return true;
		} else {
			throw new GSExeptionNotFound("Department with id: " + dept.getId());
		}
	}

	public Optional<Department> findById(Long id) {
		Validate.notNull(id, "Department id must not be null");		
		return repo.findById(id);
	}

}
