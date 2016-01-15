package gr.teicm.toulou.SnapChatyX.dao;

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
	
	public void updateSnapClientFriendList(SnapClientEntity entity) {

		getDatastore().update(
				getDatastore()
					.createQuery(SnapClientEntity.class)
					.field("_id")
					.equal(entity.getId()),
				getDatastore()
					.createUpdateOperations(SnapClientEntity.class)
					.set("friendList", entity.getFriendList())	
					
		);		
	}
	
	public void updateSnapClientBlackList(SnapClientEntity entity) {
		 
		getDatastore().update(
				getDatastore()
					.createQuery(SnapClientEntity.class)
					.field("_id")
					.equal(entity.getId()),
				getDatastore()
					.createUpdateOperations(SnapClientEntity.class)
					.set("blackList", entity.getBlackList())
					
		);		
	}
	
	public void updateSnapClientEntity(SnapClientEntity entity) {
		
		updateSnapClientFriendList(entity); 
		updateSnapClientBlackList(entity); 
		
	}
	
	public void deleteSnapClientEntity(SnapClientEntity entity) {
		getDatastore().delete(getSnapClientEntityById(entity.getId()));
	}
}
