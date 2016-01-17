package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.entity.BannedUserEntity;

public class BannedUserEntityDAO extends SnapBasicDAO<BannedUserEntity, String> {
	
	public BannedUserEntityDAO()
	{
		super(BannedUserEntity.class);
	}
	
	public void addBannedUserEntity(BannedUserEntity entity)
	{
		getDatastore().save(entity);
	}
	
	public List<BannedUserEntity> getAllBannedUserEntities()
	{
		return getDatastore().find(BannedUserEntity.class).asList();
	}
	
	public void removeBannedUserEntity(BannedUserEntity entity)
	{
		getDatastore().delete(entity);
	}

}
