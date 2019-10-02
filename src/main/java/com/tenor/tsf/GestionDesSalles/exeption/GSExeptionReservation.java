package com.tenor.tsf.GestionDesSalles.exeption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSExeptionReservation extends MyExecptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Logger logger = LogManager.getLogger(GSExeptionReservation.class);

	public GSExeptionReservation(String message) {
		super(message);

		logger.error(message);
	}
	
	
	

}
