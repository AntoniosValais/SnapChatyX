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
		
		return null != getDatastore().save(entity); 
		
	}
	
	public AdminEntity getAdminEntityById(String id){
		
		return getDatastore().get(AdminEntity.class, id);
		
	}
	
	public List<AdminEntity> getAdminEntities(){
		
		return getDatastore().find(AdminEntity.class).asList();
		
	}
	
	public boolean deleteAdminEntityById(String id){
		
		return getDatastore().delete(getAdminEntityById(id)).isUpdateOfExisting();
		
	}

}
