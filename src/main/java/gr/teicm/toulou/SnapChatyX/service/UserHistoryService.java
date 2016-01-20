package gr.teicm.toulou.SnapChatyX.service;

import java.util.ArrayList;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.dao.DataAccessException;
import gr.teicm.toulou.SnapChatyX.dao.IUserHistoryDAO;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;
import gr.teicm.toulou.SnapChatyX.model.transformer.IUserHistoryEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.IUserHistoryModelToEntityTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.UserHistoryEntityToModelTransformer;
import gr.teicm.toulou.SnapChatyX.model.transformer.UserHistoryModelToEntityTransformer;

/**
 * 
 * 
 * @since Dec 30, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryService implements IUserHistoryService {
	
	private IUserHistoryDAO dao;
	
	private final IUserHistoryModelToEntityTransformer modelToEntityTransformer;
	
	private final IUserHistoryEntityToModelTransformer entityToModelTransformer;
	
	public UserHistoryService(final IUserHistoryDAO dao) {
		
		this.dao = dao;
		
		this.modelToEntityTransformer = new UserHistoryModelToEntityTransformer();
		
		this.entityToModelTransformer = new UserHistoryEntityToModelTransformer();
		
	}
	
	public UserHistoryService() {
		
		this(null);
		
	}
	
	@Override
	public IUserHistoryDAO getUserHistoryDAO() {
		
		return this.dao;
		
	}
	
	@Override
	public void setUserHistoryDAO(final IUserHistoryDAO userHistoryDao) {
		
		this.dao = userHistoryDao;
		
	}
	
	@Override
	public IUserHistory getUserHistoryByUsername(final String username) throws ServiceException {
		
		final UserHistoryEntity entity;
		
		try {
			
			entity = dao.findUserHistoryByUsername(username);
			
		} catch (final DataAccessException ex) {
			
			throw new ServiceException("A Data Access error occured.", ex);
			
		}
		
		return entityToModelTransformer.transform(entity);
		
	}
	
	@Override
	public List<IUserHistory> getUserHistoriesAsList() throws ServiceException {
		
		final List<UserHistoryEntity> entityList;
		
		try {
			
			entityList = dao.findAllUserHistories();
			
		} catch (final DataAccessException ex) {
			
			throw new ServiceException("A Data Access error occured.", ex);
			
		}
		
		final List<IUserHistory> userHistoryList = new ArrayList<>();
		
		for (final UserHistoryEntity entity : entityList) {
			
			userHistoryList.add(entityToModelTransformer.transform(entity));
			
		}
		
		return userHistoryList;
		
	}

	@Override
	public void updateUserHistory( IUserHistory userHistory ) throws ServiceException
	{
		try
		{
			UserHistoryEntity entity = dao.findUserHistoryByUsername( userHistory.getUsername() );
			
			entity.setMessageList( userHistory.getMessageList() );
			
			dao.updateUserHistory( entity );
		}
		catch( Exception e )
		{
			throw new ServiceException( e );
		}
	}
	
}
