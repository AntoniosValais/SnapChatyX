package gr.teicm.toulou.SnapChatyX.model.transformer;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientEntityToModelTransformer {
	
	public SnapClient transform(SnapClientEntity entity) 
	{
		if(entity == null)
		{
			throw new IllegalArgumentException("Param must not be null");
		}
		
		SnapClient model = new SnapClient();
		model.setUsername(entity.getUsername());
		model.setPassword(entity.getPassword());
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		model.setLatitude(entity.getLatitude());
		model.setLongitude(entity.getLongitude());
		model.setEmail(entity.getEmail());
		model.setLocationName(entity.getLocationName());
		for(String friend : entity.getFriendList()) {
			model.addToFriendList(friend);
		}
		for(String black : entity.getBlackList()) {
			model.addToBlackList(black);
		}
		
//		model.setFriendList(entity.getFriendList());
//		model.setBlackList(entity.getBlackList());
		
		return model;
	}

}
