package gr.teicm.toulou.SnapChatyX.service;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.dao.SnapClientDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

/**
 *
 * 
 * @author Eutixia Bibo
 * @author Petros Ketsentsidis
 * @author Stamatios Tsalikis
 */
public class SnapClientService {
	
	private SnapClientDAO dao;
	
	private SnapClientModelToEntityTransformer modelToEntityTransformer;
	
	private SnapClientEntityToModelTransformer entityToModelTransformer;
	
	public SnapClientService() {
		
		this.dao = new SnapClientDAO();
		
		this.modelToEntityTransformer = new SnapClientModelToEntityTransformer();
		
		this.entityToModelTransformer = new SnapClientEntityToModelTransformer();
		
	}
	
	public SnapClientDAO getDao() {
		return dao;
	}
	public void setDao(final SnapClientDAO dao) {
		this.dao = dao;
	}
	
	public SnapClientModelToEntityTransformer getModelToEntityTransformer() {
		return modelToEntityTransformer;
	}

	public void setModelToEntityTransformer(final SnapClientModelToEntityTransformer modelToEntityTransformer) {
		this.modelToEntityTransformer = modelToEntityTransformer;
	}

	public SnapClientEntityToModelTransformer getEntityToModelTransformer() {
		return entityToModelTransformer;
	}

	public void setEntityToModelTransformer(final SnapClientEntityToModelTransformer entityToModelTransformer) {
		this.entityToModelTransformer = entityToModelTransformer;
	}

	public boolean createSnapClient(final SnapClient snapClient) {
		
		if(snapClient == null) {
			throw new IllegalArgumentException("Param must not be null!");
		}
		
		return dao.createSnapClientEntity(modelToEntityTransformer.transform(snapClient));
		
	}
	
	public SnapClient getSnapClientByUsername(final String username) throws ServiceException {
		
		if (username == null || username == "") {
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
			} catch (Exception ex) {
				return null;
			}
		}
		
		return null;
	}
	
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
				return dao.updateSnapClientEntity(entity);
			}
		}
		
		return false;
		
	}
	
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
