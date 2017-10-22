package com.corlate.corlate.core.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.corlate.corlate.core.service.CorlateDataSource;

@SlingServlet(resourceTypes = { "dummy/datasource/hit" }, generateComponent = true, methods = { "GET" }, selectors = {
		"test" }, extensions = { "html" }, generateService = true, metatype = true)
public class CsvRender extends SlingAllMethodsServlet implements CorlateDataSource {
	
	@Property(label = "Add Search Paths", description = "The Search will be done on the given paths only. Leaving it empty will return eempty results.", unbounded = PropertyUnbounded.DEFAULT)
	private static final String PROPERTY_CSV_FILE_PATH = "property.csv.file.path";

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {


		List<Resource> resourceList = new ArrayList<Resource>();

		ValueMap vm = null;

		for (int i = 1; i <= 5; i++) {
			vm = new ValueMapDecorator(new HashMap<String, Object>());
			String Value = "samplevalue" + i;
			String Text = "Sample Text " + i;
			vm.put("value", Value);
			vm.put("text", Text);

			resourceList.add(
					new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(), "nt:unstructured", vm));
		}

		// Create a DataSource that is used to populate the drop-down control
		DataSource ds = new SimpleDataSource(resourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);
	
	}

	@Override
	public String datasource() {
		// TODO Auto-generated method stub
		return null;
	}

}
