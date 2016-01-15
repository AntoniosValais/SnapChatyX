package gr.teicm.toulou.SnapChatyX.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;

import gr.teicm.toulou.SnapChatyX.WebSocketServlet.ClientServerMessage.SnapClientTextMessage;
import gr.teicm.toulou.SnapChatyX.model.entity.UserHistoryEntity;

/**
 * 
 * 
 * @since Jan 4, 2016
 * 
 * @author Stamatios Tsalikis
 */
public interface IUserHistoryDAO {
	
	Datastore datastore();
	
	void insertUserHistory(final UserHistoryEntity userHistory) throws DataAccessException;
	
	UserHistoryEntity findUserHistoryById(final String id) throws DataAccessException;
	
	UserHistoryEntity findUserHistoryByUsername(final String username) throws DataAccessException;
	
	List<UserHistoryEntity> findAllUserHistories() throws DataAccessException;
	
	void updateUserHistory(final UserHistoryEntity userHistory) throws DataAccessException;
	
	void updateUserHistory(final String username, final List<SnapClientTextMessage> userHistory) throws DataAccessException;
	
	void removeUserHistoryById(final String id) throws DataAccessException;
	
	void removeUserHistoryByUsername(final String username) throws DataAccessException;
	
}
