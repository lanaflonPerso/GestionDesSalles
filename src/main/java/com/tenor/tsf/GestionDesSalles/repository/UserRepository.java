package com.tenor.tsf.GestionDesSalles.repository;

import org.springframework.data.repository.CrudRepository;

import com.tenor.tsf.GestionDesSalles.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
