package gr.teicm.toulou.SnapChatyX.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gr.teicm.toulou.SnapChatyX.dao.SnapClientDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.entity.SnapClientEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.SnapClientModelToEntityTransformer;

public class SnapClientService {
	
	private SnapClientDAO dao;
	
	private final SnapClientModelToEntityTransformer modelToEntityTransformer;
	
	private final SnapClientEntityToModelTransformer entityToModelTransformer;
	
	public SnapClientService() {
		
		dao = new SnapClientDAO();
		
		this.modelToEntityTransformer = new SnapClientModelToEntityTransformer();
		
		this.entityToModelTransformer = new SnapClientEntityToModelTransformer();
		
	}
	
	public SnapClientDAO getDao() {
		
		return dao;
	}
	public void setDao(SnapClientDAO dao) {
		this.dao = dao;
	}
	
	public void createSnapClient(SnapClient snapClient) {
		
		SnapClientEntity entity = new SnapClientEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setUsername(snapClient.getUsername());
		entity.setPassword(snapClient.getPassword());
		entity.setFirstName(snapClient.getFirstName());
		entity.setLastName(snapClient.getLastName());
		entity.setEmail(snapClient.getEmail());
		
		List<String> friendListNames = new ArrayList<>();
		for(SnapClient sc : snapClient.getFriendList() ) {
			friendListNames.add(sc.getUsername());
		}
		entity.setFriendList(friendListNames);
		
		List<String> blackListNames = new ArrayList<>();
		for(SnapClient sc : snapClient.getBlackList() ) {
			blackListNames.add(sc.getUsername());
		}
		entity.setBlackList(blackListNames);
		
		dao.createSnapClientEntity(modelToEntityTransformer.transform(snapClient));
		
	}
	
	public SnapClient getSnapClientByUsername(String username) {
		List<SnapClientEntity> entityList = dao.getAllSnapClient();
		for(SnapClientEntity entity : entityList) {
			if(username == entity.getUsername()) {
				//FIXME : Stamati!
			}
		}
		
		/*SnapClient snapClient =new SnapClient();
		snapClient.setUsername(entity.getUsername());
		snapClient.setPassword(entity.getPassword());
		snapClient.setFirstName(entity.getFirstName());
		snapClient.setLastName(entity.getLastName());
		snapClient.setEmail(entity.getEmail());
		
		for(String u : entity.getFriendList() ) {
			snapClient.addToFriendList(this.getSnapClientByUsername(u));
			
		}
	
		for(String u : entity.getBlackList() ) {
			snapClient.addToBlackList(this.getSnapClientByUsername(u));
		}
		
		return entityToModelTransformer.transform(entity);*/
	}
	
	public boolean updateSnapClient(SnapClient snapClient)
	{
		String id;
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
		String id;
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


