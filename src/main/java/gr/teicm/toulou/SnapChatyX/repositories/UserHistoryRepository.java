package gr.teicm.toulou.SnapChatyX.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.daos.IMapedDao;
import gr.teicm.toulou.SnapChatyX.daos.UserHistoryMapedDao;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryRepository implements IUserHistoryRepository {
	
	private IMapedDao<String, IUserHistory> dao;
	
	public UserHistoryRepository() {
		
		dao = new UserHistoryMapedDao();
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IUserHistoryRepository#setUserHistoryByUsername()
	 */
	@Override
	public boolean setUserHistoryByUsername(IUserHistory userHistory) {
		
		if (! dao.isExistingEntry(userHistory.getUsername())) {
			
			return dao.put(userHistory.getUsername(), userHistory);
			
		} else {
			
			return dao.update(userHistory.getUsername(), userHistory);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IUserHistoryRepository#findElementByUsername()
	 */
	@Override
	public IUserHistory findUserHistoryByUsername(String username) {
		
		return dao.get(username);
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IUserHistoryRepository#findAllUserHistories()
	 */
	@Override
	public List<IUserHistory> findAllUserHistories() {
		
		Iterator<IUserHistory> daoAsIterator = dao.getMap().values().iterator();
		
		List<IUserHistory> userHistoryList = new ArrayList<>();
		
		while (daoAsIterator.hasNext()) {
			
			userHistoryList.add(daoAsIterator.next());
			
		}
		
		return userHistoryList;
	}
}
