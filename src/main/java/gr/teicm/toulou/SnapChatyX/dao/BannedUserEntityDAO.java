package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;

import gr.teicm.toulou.SnapChatyX.model.entity.BannedUserEntity;

/**
 * 
 * 
 * 
 * @author Stefos
 */
public class BannedUserEntityDAO extends SnapBasicDAO<BannedUserEntity, String> implements IBannedUserEntityDAO {
	
	public BannedUserEntityDAO()
	{
		super(BannedUserEntity.class);
	}
	
	@Override
	public Datastore datastore()
	{
		return getDatastore();
	}
	
	@Override
	public void addBannedUserEntity(BannedUserEntity entity)
	{
		if(entity == null)
		{
			throw new IllegalArgumentException("Entity must not be null.");
		}
		getDatastore().save(entity);
	}
	
	@Override
	public List<BannedUserEntity> getAllBannedUserEntities()
	{
		return getDatastore().find(BannedUserEntity.class).asList();
	}
	
	@Override
	public void removeBannedUserEntity(BannedUserEntity entity)
	{
		if(entity == null)
		{
			throw new IllegalArgumentException("Entity must not be null.");
		}
		getDatastore().delete(entity);
	}

}
