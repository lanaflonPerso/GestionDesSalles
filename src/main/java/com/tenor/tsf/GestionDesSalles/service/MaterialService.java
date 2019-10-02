package com.tenor.tsf.GestionDesSalles.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenor.tsf.GestionDesSalles.entity.Material;
import com.tenor.tsf.GestionDesSalles.exeption.GSExeptionNotFound;
import com.tenor.tsf.GestionDesSalles.repository.MaterialRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MaterialService {

	@Autowired
	MaterialRepository repo;
	
	public Material getMaterialById(Long id) throws GSExeptionNotFound {

		Material material = null;

		for (Material materials : repo.findAll()) {
			if (materials.getId() == id) {
				material = materials;
				break;
			}else{
				throw new GSExeptionNotFound("Material with id:"+id);
			}
		}
		return material;
	}
	
	public Material createMateriel(Material material) {//throws materielException{
		Validate.notNull(material.getLibelle(),"Label field is emty");
		Validate.notNull(material.getCategory(),"Categorie field is emty");
		
		
		log.info("Material "+material+" is created.");		
		return repo.save(material);
	}
	
	public Boolean updateMaterial(Material material) throws GSExeptionNotFound{
		Validate.notNull(material.getId(),"Material id must not be null");
		Validate.notNull(getMaterialById(material.getId()),"The ID entred is not found");
		
		if(createMateriel(material) != null){
			log.info("Material "+material.getLibelle()+" is updated");
			return true;
		}else{
			return false;
		}		
	}
	
	public Boolean deleteMaterial(Material material) throws GSExeptionNotFound{		
		Validate.notNull(material.getId(),"Material id must not be null");
		Validate.notNull(getMaterialById(material.getId()),"The ID entred is not found");
		
		repo.delete(material);
		
		if(getMaterialById(material.getId()) != null){			
			return false;
		}else{
			return true;
		}			
	}
}
