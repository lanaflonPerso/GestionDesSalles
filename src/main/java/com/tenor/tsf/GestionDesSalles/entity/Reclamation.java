package com.tenor.tsf.GestionDesSalles.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.tenor.tsf.GestionDesSalles.entity.enums.Status;

import lombok.Data;

@Entity
public @Data class Reclamation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dateReclamation;

	private String message;

	private Status status;

	@OneToOne
	private Room room;

	@OneToOne
	private User user;
}
