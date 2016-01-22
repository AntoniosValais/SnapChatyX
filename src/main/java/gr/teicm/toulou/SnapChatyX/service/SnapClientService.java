package gr.teicm.toulou.SnapChatyX.service;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.dao.ISnapClientEntityDAO;
import gr.teicm.toulou.SnapChatyX.dao.SnapClientEntityDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.ISnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.ISnapClientModelToEntityTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

/**
 *
 * 
 * @author Eutixia Bibo
 * @author Petros Ketsentsidis
 */
public class SnapClientService implements ISnapClientService {
	
	private ISnapClientEntityDAO dao;
	
	private ISnapClientModelToEntityTransformer modelToEntityTransformer;
	
	private ISnapClientEntityToModelTransformer entityToModelTransformer;
	
	public SnapClientService() {
		
		this.dao = new SnapClientEntityDAO();
		
		this.modelToEntityTransformer = new SnapClientModelToEntityTransformer();
		
		this.entityToModelTransformer = new SnapClientEntityToModelTransformer();
		
	}
	
	@Override
	public ISnapClientEntityDAO getDao() {
		return dao;
	}
	
	@Override
	public void setDao(final ISnapClientEntityDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ISnapClientModelToEntityTransformer getModelToEntityTransformer() {
		return modelToEntityTransformer;
	}
	
	@Override
	public void setModelToEntityTransformer(final ISnapClientModelToEntityTransformer modelToEntityTransformer) {
		this.modelToEntityTransformer = modelToEntityTransformer;
	}
	
	@Override
	public ISnapClientEntityToModelTransformer getEntityToModelTransformer() {
		return entityToModelTransformer;
	}
	
	@Override
	public void setEntityToModelTransformer(final ISnapClientEntityToModelTransformer entityToModelTransformer) {
		this.entityToModelTransformer = entityToModelTransformer;
	}
	
	@Override
	public boolean createSnapClient(final SnapClient snapClient) {
		
		if(snapClient == null) {
			throw new IllegalArgumentException("Param must not be null!");
		}
		
		return dao.createSnapClientEntity(modelToEntityTransformer.transform(snapClient));
		
	}
	
	@Override
	public SnapClient getSnapClientByUsername(final String username) {
		
		if (username == null || username.length() == 0) {
			throw new IllegalArgumentException("Param must not be null nor empty");
		}
		
		final List<SnapClientEntity> entityList = dao.getAllSnapClients();
		String id = null;
		for(final SnapClientEntity entity : entityList) {
			if(username == entity.getUsername()) {
				id = entity.getId();
				break;
			}
			
		}
		
		if (id != null) {
			try {
				final SnapClientEntity entity = dao.getSnapClientEntityById(id);
				return entityToModelTransformer.transform(entity);
			} catch (final Exception ex) {
				return null;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean updateSnapClient(final SnapClient snapClient) {
		
		if (snapClient == null) {
			throw new IllegalArgumentException("Param must not be null.");
		}
		
		if (null != snapClient.getUsername()) {		
			String id = null;
			for(final SnapClientEntity entity : dao.getAllSnapClients()) {
				if(entity.getUsername().equals(snapClient.getUsername())) {
					id = entity.getId();
				}
			}
			
			if(id != null) {
				final SnapClientEntity entity = dao.getSnapClientEntityById(id);
				entity.setFriendList(snapClient.getFriendList());
				entity.setBlackList(snapClient.getBlackList());
				
				return dao.updateSnapClientEntity(entity);
			}
		}
		
		return false;
		
	}
	
	@Override
	public boolean deleteSnapClientByUsername(final String username) {
		
		if(username == null || username.length() == 0) {
			throw new IllegalArgumentException("Param must not be null nor empty.");
		}
		
		String id = null;
		for(SnapClientEntity entity : dao.getAllSnapClients()) {
			if(username.equals(entity.getUsername())) {
				id = entity.getId();
			}
		}
		
		if(id != null) {
			SnapClientEntity entity = dao.getSnapClientEntityById(id);
			return dao.deleteSnapClientEntity(entity);
		}
		
		return false;
		
	}
	
}
