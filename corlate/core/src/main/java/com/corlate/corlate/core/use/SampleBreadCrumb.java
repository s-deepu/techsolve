package com.corlate.corlate.core.use;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class SampleBreadCrumb extends WCMUsePojo{
	
	private String[] parentPagesPath;
	private Map<String, String> pageMap;

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void activate() throws Exception {
		log.error("Starts BreadCrumb"); 
		pageMap = new LinkedHashMap<String, String>();
		String currentPagePath = getCurrentPage().getPath().toString();
		parentPagesPath = currentPagePath.split("/");
		for (int i = 0; i < parentPagesPath.length; i++) {
			pageMap.put(parentPagesPath[i], "/"+parentPagesPath[i]+".html");
		}
		log.error("This is the page Map: {}",pageMap);
	}
	
	public String[] getParentPagesPath(){
		return parentPagesPath;
	}
	
	public Map<String, String> getPageMap() {
		return pageMap;
	}

}
