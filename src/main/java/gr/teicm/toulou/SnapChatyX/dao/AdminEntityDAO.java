package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.entity.AdminEntity;

/**
 * @author NTINA
 *
 */
public class AdminEntityDAO extends SnapBasicDAO<AdminEntity, String> {

	public AdminEntityDAO() {
		
		super(AdminEntity.class);
		
	}
	
	public boolean addAdminEntity(AdminEntity entity) {
		
		if (entity == null) {
			throw new IllegalArgumentException("Param must not be null or empty.");
		}
		
		return null != getDatastore().save(entity); 
		
	}
	
	public AdminEntity getAdminEntityById(String id) {
		
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Id must not be null or empty.");
		}
		
		return getDatastore().get(AdminEntity.class, id);
		
	}
	
	public List<AdminEntity> getAdminEntities(){
		
		return getDatastore().find(AdminEntity.class).asList();
		
	}
	
	public boolean deleteAdminEntityById(String id) {
		
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Id must not be null or empty.");
		}
		
		AdminEntity entity = getAdminEntityById(id);
		
		getDatastore().delete(entity);
		
		return null != entity;
		
	}

}
