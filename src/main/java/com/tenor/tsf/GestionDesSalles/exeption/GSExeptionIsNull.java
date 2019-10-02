package com.tenor.tsf.GestionDesSalles.exeption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GSExeptionIsNull extends MyExecptions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Logger logger = LogManager.getLogger(GSExeptionIsNull.class);

	public GSExeptionIsNull(String message) {
		super(message);

		logger.error(message + " cant be null !");
	}

}
