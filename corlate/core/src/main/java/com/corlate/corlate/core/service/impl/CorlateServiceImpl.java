package com.corlate.corlate.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corlate.corlate.core.service.CorlateService;

@Component(immediate = true, name = "CorlateSevice")
@Service(value = CorlateService.class)
public class CorlateServiceImpl implements CorlateService {

	@Reference
	SlingRepository repository;
	@Reference
	ResourceResolverFactory resolverFactory;

	private static String userID;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public String callMethod() {

		Session loginService;
		try {
			loginService = repository.loginService("CorlateService", null);

			Map<String, Object> user = new HashMap<String, Object>();
			user.put("user.jcr.session", loginService);

			ResourceResolver resourceResolver;

			resourceResolver = resolverFactory.getResourceResolver(user);

			userID = resourceResolver.getUserID();
			log.info("*************** Corlate on Service ************");
			log.info("*************** " + userID + " ************");

		} catch (javax.jcr.LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userID;
	}

}
