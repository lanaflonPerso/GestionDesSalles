package com.tenor.tsf.GestionDesSalles.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.Reclamation;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.repository.ReclamationRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReclamationService {
	
	@Autowired
	ReclamationRepository repo;
	
	public List<Reclamation> AllReclamations(){
		return (List<Reclamation>) repo.findAll();
	}
	
	public Reclamation getReclamationById(Long id) throws GSExeptionNotFound{
		Reclamation reclamation = null;
		
		for(Reclamation reclamations: repo.findAll()){
			if(reclamations.getId()==id){
				reclamation = reclamations;
			}else{
				throw new GSExeptionNotFound("Exception with id: ");
			}
		}
		return reclamation;
	}

	
	public Reclamation createReclamation(Reclamation reclamation){
		Validate.notEmpty(reclamation.getMessage(),"Please enter your reclamation message.");
		Validate.notNull(reclamation.getRoom(),"Please enter room claimed");
		
		log.info("Claimed for room "+reclamation.getRoom().getLabel()+" is created in "+reclamation.getDateReclamation());		
		return repo.save(reclamation);		
	}
	
	
}
