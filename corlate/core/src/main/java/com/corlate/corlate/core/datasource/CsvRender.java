package com.corlate.corlate.core.datasource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.corlate.corlate.core.service.CorlateDataSource;

@SlingServlet(resourceTypes = { "dummy/datasource/hit" }, methods = {
		"GET" }, generateComponent = true, generateService = true, metatype = true)
public class CsvRender extends SlingAllMethodsServlet {
	@Reference
	CorlateDataSource csvrender;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private static String states[];
	private static String paths[];

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		

		Map<Object, Object> datasource = csvrender.datasource();

		List<Resource> resourceList = new ArrayList<Resource>();
		ValueMap vm = null;
		for (int i = 0; i < 5; i++) {
			vm = new ValueMapDecorator(new HashMap<String, Object>());
			String Value = (String) datasource.get(states[i]);
			String Text = (String) datasource.get(paths[i]);
			vm.put("value", Value);
			vm.put("text", Text);

			resourceList.add(
					new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(), "nt:unstructured", vm));
		}

		// Create a DataSource that is used to populate the drop-down control
		DataSource ds = new SimpleDataSource(resourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);

	}

}
