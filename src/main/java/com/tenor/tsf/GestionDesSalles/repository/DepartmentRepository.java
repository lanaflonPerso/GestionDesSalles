package com.tenor.tsf.GestionDesSalles.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tenor.tsf.GestionDesSalles.entity.Department;

@Repository
@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Long>{

}
