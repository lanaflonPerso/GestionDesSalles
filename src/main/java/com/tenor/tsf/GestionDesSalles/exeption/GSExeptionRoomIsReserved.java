package com.tenor.tsf.GestionDesSalles.exeption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSExeptionRoomIsReserved extends MyExecptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Logger logger = LogManager.getLogger(GSExeptionRoomIsReserved.class);

	public GSExeptionRoomIsReserved(String message) {
		super(message+" already reserved  in selected date");

		logger.error(message+" already reserved in selected date");
	}
	
	
	

}
