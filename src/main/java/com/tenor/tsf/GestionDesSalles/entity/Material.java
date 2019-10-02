package com.tenor.tsf.GestionDesSalles.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.tenor.tsf.GestionDesSalles.entity.enums.Category;

import lombok.Data;

@Entity
public @Data class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String libelle;
	
	private Category category;
	
	@ManyToOne
	private Room room;

}
