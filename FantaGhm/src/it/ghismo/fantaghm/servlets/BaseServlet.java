package it.ghismo.fantaghm.servlets;

import javax.servlet.ServletException;

import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.Log;


public class BaseServlet extends CommonRestServlet {
	private static final long serialVersionUID = 8629830617882973251L;

	public  static final String SERVLET_NAME = "FantaGhm - BaseServlet";
	
	@Override
    public synchronized void init() throws ServletException {
		if(!isInitialised) {
	    	super.init();
			
			
			
			// **************** conclusione ***************
			
			Log.info("************************************************************************************");
			Log.info("Inizializzazione Servlet " + SERVLET_NAME + "...");
			Log.info("************************************************************************************");

		}
    }
}
