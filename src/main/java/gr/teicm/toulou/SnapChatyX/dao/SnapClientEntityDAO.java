package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.mongodb.morphia.query.UpdateResults;

import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;

/**
 * 
 * @author Orestis Zagkoumidis
 */
public class SnapClientEntityDAO extends SnapBasicDAO<SnapClientEntity, String> implements ISnapClientEntityDAO {

	public SnapClientEntityDAO() {
		
		super(SnapClientEntity.class);
		
	}
	
	@Override
	public boolean createSnapClientEntity(final SnapClientEntity entity) {
		
		if(null == entity) {
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		if(null == getSnapClientEntityById(entity.getId())) {
			getDatastore().save(entity);
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public SnapClientEntity getSnapClientEntityById(final String id) {
		
		return getDatastore().get(SnapClientEntity.class, id);
		
	}
	
	@Override
	public List<SnapClientEntity> getAllSnapClients() {
		
		return getDatastore().find(SnapClientEntity.class).asList();
		
	}
	
	@Override
	public boolean updateSnapClientFriendList(final SnapClientEntity entity) {
		
		if(null == entity){
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		final UpdateResults updateResults = getDatastore().update(
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
	
	@Override
	public boolean updateSnapClientBlackList(final SnapClientEntity entity) {
		 
		if(null == entity){
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		final UpdateResults updateResults = getDatastore().update(
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
	
	@Override
	public boolean updateSnapClientEntity(final SnapClientEntity entity) {
		//FIXME : den kanei update ola ta properties
		if(updateSnapClientFriendList(entity) && updateSnapClientBlackList(entity)) {
			
			return true;
			
		} 
		
		return false;
		
	}
	
	@Override
	public boolean deleteSnapClientEntity(final SnapClientEntity entity) {
		final SnapClientEntity scEntity = getSnapClientEntityById(entity.getId());
		
		if(null != scEntity) {
			
			getDatastore().delete(scEntity);
			return true;
			
		}
		
		return false;
	}
	
}
