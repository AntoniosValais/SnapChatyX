package gr.teicm.toulou.SnapChatyX.service;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.dao.SnapClientDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

public class SnapClientService {
	
	private SnapClientDAO dao;
	
	private SnapClientModelToEntityTransformer modelToEntityTransformer;
	
	private SnapClientEntityToModelTransformer entityToModelTransformer;
	
	public SnapClientService() {
		
		dao = new SnapClientDAO();
		
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
//		SnapClientEntity entity = new SnapClientEntity();
//		entity.setId(UUID.randomUUID().toString());
//		entity.setUsername(snapClient.getUsername());
//		entity.setPassword(snapClient.getPassword());
//		entity.setFirstName(snapClient.getFirstName());
//		entity.setLastName(snapClient.getLastName());
//		entity.setEmail(snapClient.getEmail());
//		
//		List<String> friendListNames = new ArrayList<>();
//		for(SnapClient sc : snapClient.getFriendList() ) {
//			friendListNames.add(sc.getUsername());
//		}
//		entity.setFriendList(snapClient.getFriendList());
//		
//		List<String> blackListNames = new ArrayList<>();
//		for(SnapClient sc : snapClient.getBlackList() ) {
//			blackListNames.add(sc.getUsername());
//		}
//		entity.setBlackList(snapClient.getBlackList());
//		
		return dao.createSnapClientEntity(modelToEntityTransformer.transform(snapClient));
		
	}
	
	public SnapClient getSnapClientByUsername(String username) throws ServiceException {
		if (username == null || username == "") {
			throw new IllegalArgumentException("Param must not be null nor empty");
			
		}
		List<SnapClientEntity> entityList = dao.getAllSnapClients();
		SnapClientEntity entity = null;
		for(SnapClientEntity e : entityList) {
			if(username == e.getUsername()) {
				entity = e;
				break;
			}
			
		}
		try { 
//			SnapClient snapClient =new SnapClient();
//			snapClient.setUsername(entity.getUsername());
//			snapClient.setPassword(entity.getPassword());
//			snapClient.setFirstName(entity.getFirstName());
//			snapClient.setLastName(entity.getLastName());
//			snapClient.setEmail(entity.getEmail());
//			
//			for(String u : entity.getFriendList() ) {
//				snapClient.addToFriendList(u);
//				
//			}
//		
//			for(String u : entity.getBlackList() ) {
//				snapClient.addToBlackList(u);
//			}
			
			return entityToModelTransformer.transform(entity);
		}
		catch (NullPointerException ex) {
			throw new ServiceException("Entity not found", ex);
		}
		
		
		
	}
	
	public boolean updateSnapClient(SnapClient snapClient)
	{
		String id = null;
		for(SnapClientEntity sc : dao.getAllSnapClients())
		{
			if(snapClient.getUsername() == sc.getUsername())
			{
				id = sc.getId();
			}
		}
		if(id != null)
		{
			SnapClientEntity entity = dao.getSnapClientEntityById(id);
			return dao.updateSnapClientEntity(entity);
		}
		return false;
		
	}
	public boolean deleteSnapClient(SnapClient snapClient)
	{
		if(snapClient == null)
		{
			throw new IllegalArgumentException("Param must not be null");
		}
		String id = null;
		for(SnapClientEntity sc : dao.getAllSnapClients())
		{
			if(snapClient.getUsername() == sc.getUsername())
			{
				id = sc.getId();
			}
		}
		
		if(id != null)
		{
			SnapClientEntity entity = dao.getSnapClientEntityById(id);
			return dao.deleteSnapClientEntity(entity);
		}
		return false;
		
	}
	
	
	
}


