package gr.teicm.toulou.SnapChatyX.service;

import gr.teicm.toulou.SnapChatyX.dao.ISnapClientEntityDAO;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;
import gr.teicm.toulou.SnapChatyX.model.transformer.ISnapClientEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.ISnapClientModelToEntityTransformer;

/**
 * 
 * 
 * @author Stamatios Tsalikis
 */
public interface ISnapClientService {
	
	ISnapClientEntityDAO getDao();
	
	void setDao(ISnapClientEntityDAO dao);
	
	ISnapClientModelToEntityTransformer getModelToEntityTransformer();
	
	void setModelToEntityTransformer(ISnapClientModelToEntityTransformer modelToEntityTransformer);
	
	ISnapClientEntityToModelTransformer getEntityToModelTransformer();
	
	void setEntityToModelTransformer(ISnapClientEntityToModelTransformer entityToModelTransformer);
	
	boolean createSnapClient(SnapClient snapClient);
	
	SnapClient getSnapClientByUsername(String username);
	
	boolean updateSnapClient(SnapClient snapClient);
	
	boolean deleteSnapClientByUsername(String username);
	
}
