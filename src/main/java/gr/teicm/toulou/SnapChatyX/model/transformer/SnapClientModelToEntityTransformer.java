package gr.teicm.toulou.SnapChatyX.model.transformer;

import java.util.UUID;

import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

/**
 * 
 * 
 * @author Stefanos Douliakas
 */
public class SnapClientModelToEntityTransformer implements ISnapClientModelToEntityTransformer {
	
	@Override
	public SnapClientEntity transform(SnapClient model) 
	{
		if(model == null)
		{
			throw new IllegalArgumentException("Param must not be null");
		}
		
		SnapClientEntity entity = new SnapClientEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setUsername(model.getUsername());
		entity.setPassword(model.getPassword());
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		entity.setLatitude(model.getLatitude());
		entity.setLongitude(model.getLongitude());
		entity.setEmail(model.getEmail());
		entity.setLocationName(model.getLocationName());
		entity.setFriendList(model.getFriendList());
		entity.setBlackList(model.getBlackList());
		
		return entity;
	}

}
