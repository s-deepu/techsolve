package com.corlate.corlate.core.use;

import com.adobe.cq.sightly.WCMUsePojo;
import com.corlate.corlate.core.service.AssetMetadata;

public class TestUse extends WCMUsePojo {

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		AssetMetadata service = getSlingScriptHelper().getService(AssetMetadata.class);
		service.getAssetMetadata(getResourceResolver());
		
	}
	
}
