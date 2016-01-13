package gr.teicm.toulou.SnapChatyX.service;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.dao.IUserHistoryDAO;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;

/**
 * 
 * 
 * @since Dec 30, 2015
 * 
 * @author Stamatios Tsalikis
 */
public interface IUserHistoryService {
	
	IUserHistoryDAO getUserHistoryDAO();
	
	void setUserHistoryDAO(final IUserHistoryDAO userHistoryDao);
	
	IUserHistory getUserHistoryByUsername(final String username) throws ServiceException;
	
	List<IUserHistory> getUserHistoriesAsList() throws ServiceException;
	
}
