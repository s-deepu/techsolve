package com.corlate.corlate.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SlingServlet( resourceTypes={"/apps/corlate/components/page/basepage"}, generateComponent=true, methods={"GET"}, selectors = {"test"},extensions={"html"}, generateService=true, metatype=true 
		)
@Properties({
	@Property(label = "test Servlet Label", description = "This is to confirm that servlet has resolved for resourceType", name = "ResourceServlet")
})
public class CorlateTestResourceServlet extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
	response.getWriter().write("This is a all Method resourceType Servlet");
	
	}

}
