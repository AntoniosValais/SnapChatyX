package gr.teicm.toulou.SnapChatyX.repositories;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.IUserHistory;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public interface IUserHistoryRepository {
	
	boolean setUserHistoryByUsername(IUserHistory userHistory);
	
	IUserHistory findUserHistoryByUsername(String username);
	
	List<IUserHistory> findAllUserHistories();
	
}
