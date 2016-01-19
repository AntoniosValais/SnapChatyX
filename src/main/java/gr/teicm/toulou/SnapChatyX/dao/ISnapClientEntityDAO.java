package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

/**
 * 
 * 
 * @author Stamatios Tsalikis
 */
public interface ISnapClientEntityDAO {
	
	boolean createSnapClientEntity(SnapClientEntity entity);
	
	SnapClientEntity getSnapClientEntityById(String id);
	
	List<SnapClientEntity> getAllSnapClients();
	
	boolean updateSnapClientFriendList(SnapClientEntity entity);
	
	boolean updateSnapClientBlackList(SnapClientEntity entity);
	
	boolean updateSnapClientEntity(SnapClientEntity entity);
	
	boolean deleteSnapClientEntity(SnapClientEntity entity);
	
}
