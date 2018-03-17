package com.corlate.corlate.core.models;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Model;


@Model(adaptables=Resource.class)
public class CorlateModel {

	@Reference
	ResourceResolverFactory resolverFactory;

	public void justLike(){

	}


}
