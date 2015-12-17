package gr.teicm.toulou.SnapChatyX.services;

import java.util.List;

import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.repositories.IUserHistoryRepository;
import gr.teicm.toulou.SnapChatyX.repositories.UserHistoryRepository;

/**
 * 
 * 
 * @since Dec 8, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserService {
	
	private static UserService instance;
	
	private final IUserHistoryRepository userHistoryRepository;
	
	private UserService() {
		
		userHistoryRepository = new UserHistoryRepository();
		
	}
	
	public static UserService getInstance() {
		
		if (instance.equals(null)) {
			
			instance = new UserService();
			
		}
		
		return instance;
		
	}
	
	public void setUserHistoryByUsername(UserHistory userHistory) {
		
		userHistoryRepository.setUserHistoryByUsername(userHistory);
		
	}
	
	public IUserHistory getUserHistoryByUsername(String username) {
		
		return userHistoryRepository.findUserHistoryByUsername(username);
		
	}
	
	public List<IUserHistory> findAllUserHistories() {
		
		return userHistoryRepository.findAllUserHistories();
		
	}
	
}
