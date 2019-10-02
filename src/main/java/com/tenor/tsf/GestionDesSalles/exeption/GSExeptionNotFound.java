package com.tenor.tsf.GestionDesSalles.exeption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSExeptionNotFound extends MyExecptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Logger logger = LogManager.getLogger(GSExeptionNotFound.class);

	public GSExeptionNotFound(String message) {
		super(message+ " not found !");

		logger.error(message + " not found !");
	}

}
