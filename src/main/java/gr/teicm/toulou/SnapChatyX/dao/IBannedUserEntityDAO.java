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
public interface IBannedUserEntityDAO {
	
	Datastore datastore();

	void addBannedUserEntity(BannedUserEntity entity);

	List<BannedUserEntity> getAllBannedUserEntities();

	void removeBannedUserEntity(BannedUserEntity entity);

}
