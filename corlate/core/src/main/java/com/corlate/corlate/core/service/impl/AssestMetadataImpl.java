package com.corlate.corlate.core.service.impl;

import java.util.Map;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corlate.corlate.core.service.AssetMetadata;
import com.day.cq.dam.api.Rendition;

@Component
@Service
public class AssestMetadataImpl implements AssetMetadata{

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void getAssetMetadata(ResourceResolver resolver) {
		// TODO Auto-generated method stub
		Resource resource = resolver.getResource("/content/dam/corlate/image.jpg");
		/*Asset adaptTo = resource.adaptTo(Asset.class);
		com.adobe.granite.asset.api.AssetMetadata assetMetadata = adaptTo.getAssetMetadata();
	*/
		com.day.cq.dam.api.Asset asset = resource.adaptTo(com.day.cq.dam.api.Asset.class);
		Map<String, Object> metadata = asset.getMetadata();
		String object = (String) metadata.get("xmp:CreatorTool");
		log.error("This is the metadata xmp:CreatorTool property of an asset: {}",object);
		Rendition original = asset.getOriginal();
		ValueMap originalProperties = original.getProperties();
		String jcrLastModifiedBy = originalProperties.get("jcr:lastModifiedBy", String.class);
		log.error("This is the metadata jcr:lastModifiedBy property of an asset: {}",jcrLastModifiedBy);
		
	
	}

}
