package com.tenor.tsf.GestionDesSalles.controller;

import java.util.List;
import java.util.Optional;

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

import com.tenor.tsf.GestionDesSalles.entity.Department;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionAlreadyExist;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.service.DepartmentService;

@RestController
@RequestMapping(value = "/Department")
public class DepartmentController {

	@Autowired
	DepartmentService service;

	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartement() {

		return new ResponseEntity<List<Department>>(service.getAllDepartment(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> findById(@PathVariable Long id) {
		Optional<Department> dept = service.findById(id);

		return new ResponseEntity<Department>(dept.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Department> create(@RequestBody Department dept) {
		Department department = null;
		try {
			department = service.createNewDepartement(dept);
		} catch (Exception e) {
			return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Department>(department, HttpStatus.CREATED);
	}

	@PutMapping // (value = "/update")
	public ResponseEntity<Void> updateDepartement(@RequestBody Department department)
			throws GSExeptionAlreadyExist, GSExeptionNotFound {
		Optional<Department> dept = service.findById(department.getId());
		if (!dept.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		service.updateDepartment(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping // (value = "/delete")
	public ResponseEntity<Void> deleteDepartement(@RequestBody Department dept) throws GSExeptionNotFound {
		Optional<Department> dep = service.findById(dept.getId());
		if (!dep.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		service.deleteDepartment(dept);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
