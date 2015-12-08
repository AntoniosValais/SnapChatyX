package gr.teicm.toulou.SnapChatyX.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.teicm.toulou.SnapChatyX.factories.RepositoryFactory;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;
import gr.teicm.toulou.SnapChatyX.repositories.IRepository;
import gr.teicm.toulou.SnapChatyX.repositories.UserHistoryRepository;

/**
 * @since Dec 8, 2015
 * 
 * @author Stamatios Tsalikis
 * 
 */
public class UserService {
	
	private static UserService instance;
	
	private final Map<IRepository.RepositoryType, IRepository> repositoryMap;
	
	private UserService() {
		repositoryMap = new HashMap<>();
		
		try {
			repositoryMap.put(
					IRepository.RepositoryType.USER_HISTORY,
					RepositoryFactory.getInstance(UserHistoryRepository.class)
			);
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}
	
	public static UserService getInstance() {
		if (instance.equals(null)) {
			instance = new UserService();
		}
		
		return instance;
	}
	
	public void addUserHistory(UserHistory userHistory) {
		repositoryMap.get(IRepository.RepositoryType.USER_HISTORY).addElement(userHistory);
	}
	
	public UserHistory getUserHistoryByUsername(String username) {
		UserHistory userHistory =
				(UserHistory) repositoryMap
				.get(IRepository.RepositoryType.USER_HISTORY)
				.findElementByUsername(username);
		
		return userHistory;
	}
	
	public List<UserHistory> findAllUserHistories() {
		List<UserHistory> userHistoryList =
				(List<UserHistory>) repositoryMap
				.get(IRepository.RepositoryType.USER_HISTORY)
				.findAllElements();
		
		return userHistoryList;
	}
	
}
