package com.corlate.corlate.core.models;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Source;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class)
public class NavigationSlingModel {

	@Inject @Optional
	private String navLink;
	
	@Inject @Optional
	private String fileReference;
	
	@Inject @Source("sling-object")
    private ResourceResolver resourceResolver;
	
	private boolean isFileReferenceExists = true;
	
	public boolean isFileReferenceExists() {
		return isFileReferenceExists;
	}

	public void setFileReferenceExists(boolean isFileReferenceExists) {
		this.isFileReferenceExists = isFileReferenceExists;
	}

	@PostConstruct
	private void init() {
		Resource resource = resourceResolver.getResource(getNavLink());
		Page page = resourceResolver.adaptTo(PageManager.class).getContainingPage(resource);
		if (null != resource && null != page) {
			ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
			Iterator<String> iterator = valueMap.keySet().iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				String object = (String) valueMap.get(next);
				object = object + "," + page.getPath();
				
				valueMap.put(next, object);
			}
		} else {
			this.setFileReferenceExists(false);
		}
	}

	public String getNavLink() {
		return navLink;
	}

	public String getFileReference() {
		return fileReference;
	}
	
	
	
}
