package gr.teicm.toulou.SnapChatyX.model;

import java.util.List;

public interface IUserHistoryDAO {
	
	Boolean usernameExistsInUserHistoryList(String username);
	
	Boolean addUserHistoryByUsername(IUserHistory userHistory);
	
	IUserHistory getUserHistoryByUsername(String username);
	
	Boolean updateUserHistoryByUsername(IUserHistory userHistory);
	
	Boolean deleteUserHistoryByUsername(String username);
	
	List<IUserHistory> getUserHistoryList();
	
}
