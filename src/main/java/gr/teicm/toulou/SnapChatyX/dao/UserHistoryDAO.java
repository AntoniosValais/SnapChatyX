package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.ValidationException;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * 
 * 
 * @since Jan 5, 2016
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryDAO extends SnapBasicDAO<UserHistoryEntity, String> implements IUserHistoryDAO {
	
	public UserHistoryDAO() {
		
		super(UserHistoryEntity.class);
		
	}
	
	@Override
	public Datastore datastore() {
		
		return getDatastore();
		
	}
	
	@Override
	public void insertUserHistory(final UserHistoryEntity entity) throws DataAccessException {
		
		if (entity == null) {
			
			throw new DataAccessException("The UserHistoryEntity object param should not be null.");
			
		}
		
		if (entity.getId() == null || entity.getId().length() == 0) {
			
			throw new DataAccessException(
					"The id field of the UserHistoryEntity object param should not be null nor empty."
			);
			
		}
		
		getDatastore().save(entity);
		
	}
	
	@Override
	public UserHistoryEntity findUserHistoryById(final String id) throws DataAccessException {
		
		if (id == null) {
			
			throw new DataAccessException("The id param should not be null.");
			
		}
		
		final UserHistoryEntity entity;
		
		try {
			
			// Equivalent to -> getDatastore().find(UserHistoryEntity.class, "_id", id).get();
			entity = getDatastore().get(UserHistoryEntity.class, id);
			
		} catch (final ValidationException ex) {
			
			throw new DataAccessException(ex);
			
		}
		
		if (entity != null) {
			
			return entity;
			
		}
		
		throw new DataAccessException(
				"No UserHistoryEntity object was found with " + Mapper.ID_KEY + " = \"" + id + "\"."
		);
		
	}
	
	@Override
	public UserHistoryEntity findUserHistoryByUsername(final String username) throws DataAccessException {
		
		if (username == null || username.length() == 0) {
			
			throw new DataAccessException("The username param should not be null nor empty.");
			
		}
		
		final UserHistoryEntity entity =
				getDatastore().find(UserHistoryEntity.class, "username", username).get();
		
		if (entity != null) {
			
			return entity;
			
		}
		
		throw new DataAccessException(
				"No UserHistoryEntity object was found with username = \"" + username + "\"."
		);
		
	}
	
	@Override
	public List<UserHistoryEntity> findAllUserHistories() throws DataAccessException {
		
		final List<UserHistoryEntity> allEntities = getDatastore().find(UserHistoryEntity.class).asList();
		
		if (allEntities != null) {
			
			return allEntities;
			
		} else {
			
			throw new DataAccessException("The UserHistoryEntity list is null.");
			
		}
		
	}
	
	@Override
	public void updateUserHistory(final UserHistoryEntity userHistory) throws DataAccessException {
		
		if (userHistory == null) {
			
			throw new DataAccessException("The UserHistoryEntity object param should not be null.");
			
		}
		
		getDatastore().update(
				getDatastore()
					.createQuery(UserHistoryEntity.class)
					.field("username")
					.equal(userHistory.getUsername()),
				getDatastore()
					.createUpdateOperations(UserHistoryEntity.class)
					.set("messageList", userHistory.getMessageList())
		);
		
	}
	
	@Override
	public void updateUserHistory(final String username, final List<SnapClientTextMessage> messageList)
			throws DataAccessException {
		
		if (username == null || username.length() == 0) {
			
			throw new DataAccessException("The username param should not be null nor empty.");
			
		}
		
		if (messageList == null || messageList.size() == 0) {
			
			throw new DataAccessException("The SnapClientTextMessage list param should not be null nor empty.");
			
		}
		
		getDatastore().update(
				getDatastore()
					.createQuery(UserHistoryEntity.class)
					.field("username")
					.equal(username),
				getDatastore()
					.createUpdateOperations(UserHistoryEntity.class)
					.set("messageList", messageList)
		);
		
	}
	
	@Override
	public void removeUserHistoryById(final String id) throws DataAccessException {
		
		getDatastore().delete(findUserHistoryById(id));
		
	}
	
	@Override
	public void removeUserHistoryByUsername(final String username) throws DataAccessException {
		
		getDatastore().delete(findUserHistoryByUsername(username));
		
	}
	
}
