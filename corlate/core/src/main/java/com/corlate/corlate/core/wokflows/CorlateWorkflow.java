package com.corlate.corlate.core.wokflows;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.asset.api.AssetManager;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(immediate=true, metatype=true )
@Service
@Properties(
		{
			@Property(name="process.label", value="Corlate WorkFlow", propertyPrivate=true)
		})
public class CorlateWorkflow implements WorkflowProcess{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Reference
	ResourceResolverFactory resolverFactory;
	
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap map) throws WorkflowException {
		// Workflow to Move the asset from one payload to another payload
		Session jcrSession = session.adaptTo(Session.class);
		String payload = (String) item.getWorkflowData().getPayload();
		log.debug("Payload of an workflow ***** ----> ", payload);
		
		Map<String, Object> wfuser = new HashMap<String, Object>();
		wfuser.put("user.jcr.session", jcrSession);
		try {
			ResourceResolver resourceResolver = resolverFactory.getResourceResolver(wfuser);
			log.debug("Resolver ****", resourceResolver.getUserID());
			AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
			log.debug("AssesManger ======> ", assetManager.assetExists(payload));
			assetManager.moveAsset(payload, "/content/test.jpg");
			log.debug("Successfully Moved Asset to a particular Place");
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
