package com.corlate.corlate.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corlate.corlate.core.service.CorlateService;

@SlingServlet(paths = {"/bin/servlet"}, generateComponent = true, generateService = true)



public class CorlateTestServlet extends SlingAllMethodsServlet{

	//Git Commiting
	
	@Reference
	CorlateService service;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		//getServletContext().getRequestDispatcher(arg0);
		log.info("*************** Corlate Servlet Called ************");
		response.getWriter().write("Corlate Servlet Called according to the Hit");
		String calledUser = service.callMethod();
		log.info("*************** "+ calledUser +" ************");

	}
}
