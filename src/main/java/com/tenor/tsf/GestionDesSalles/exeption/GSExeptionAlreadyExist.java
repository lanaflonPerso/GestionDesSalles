package com.tenor.tsf.GestionDesSalles.exeption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSExeptionAlreadyExist extends MyExecptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Logger logger = LogManager.getLogger(GSExeptionAlreadyExist.class);

	public GSExeptionAlreadyExist(String message) {
		super(message + " already exist!");

		logger.error(message + " already exist!");
	}

}
