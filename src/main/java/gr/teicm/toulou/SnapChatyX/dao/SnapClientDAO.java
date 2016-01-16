package gr.teicm.toulou.SnapChatyX.dao;

import org.mongodb.morphia.query.UpdateResults;

import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientDAO extends SnapBasicDAO<SnapClientEntity, String> {

	public SnapClientDAO() {
		
		super(SnapClientEntity.class);
		
	}
	
	public boolean createSnapClientEntity(SnapClientEntity entity) {
		
		if(null == entity) {
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		if(null == getSnapClientEntityById(entity.getId())) {
			getDatastore().save(entity);
			return true;
		}
		
		return false;
		
	}
	
	public SnapClientEntity getSnapClientEntityById(String id) {
		
		return getDatastore().get(SnapClientEntity.class, id );
		
	}
	
	public boolean updateSnapClientFriendList(SnapClientEntity entity) {
		
		if(null == entity){
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		UpdateResults updateResults = getDatastore().update(
				getDatastore()
					.createQuery(SnapClientEntity.class)
					.field("_id")
					.equal(entity.getId()),
				getDatastore()
					.createUpdateOperations(SnapClientEntity.class)
					.set("friendList", entity.getFriendList())	
					
		);
		
		if(null != updateResults) {
			
			if(updateResults.getUpdatedExisting()) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public boolean updateSnapClientBlackList(SnapClientEntity entity) {
		 
		if(null == entity){
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		UpdateResults updateResults = getDatastore().update(
				getDatastore()
					.createQuery(SnapClientEntity.class)
					.field("_id")
					.equal(entity.getId()),
				getDatastore()
					.createUpdateOperations(SnapClientEntity.class)
					.set("blackList", entity.getBlackList())
					
		);
		
		if(null != updateResults) {
			
			if(updateResults.getUpdatedExisting()) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public boolean updateSnapClientEntity(SnapClientEntity entity) {
		
		if(updateSnapClientFriendList(entity) && updateSnapClientBlackList(entity)) {
			
			return true;
			
		} 
		
		return false;
		
	}
	
	public boolean deleteSnapClientEntity(SnapClientEntity entity) {
		SnapClientEntity scEntity = getSnapClientEntityById(entity.getId());
		
		if(null != scEntity) {
			
			getDatastore().delete(scEntity);
			return true;
			
		}
		
		return false;
		
	}
}
