package gr.teicm.toulou.SnapChatyX.dao;

import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

public class SnapClientDAO extends SnapBasicDAO<SnapClientEntity, String> {

	public SnapClientDAO() {
		
		super(SnapClientEntity.class);
		
	}
	
	public void createSnapClientEntity(SnapClientEntity entity) {
		
		getDatastore().save(entity);
		
	}
	
	public SnapClientEntity getSnapClientEntityById(String id) {
		
		return getDatastore().get(SnapClientEntity.class, id );
		
	}
	
	public void updateSnapClientEntity(SnapClientEntity entity) {
		//FIXME:2nd .set doesn't work! 
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
	
	public void deleteSnapClientEntity(SnapClientEntity entity) {
		getDatastore().delete(getSnapClientEntityById(entity.getId()));
	}
}
