package gr.teicm.toulou.SnapChatyX.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gr.teicm.toulou.SnapChatyX.daos.IMapedDao;
import gr.teicm.toulou.SnapChatyX.daos.UserHistoryMapedDao;
import gr.teicm.toulou.SnapChatyX.model.UserHistory;

/**
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryRepository implements IRepository {
	
	private IMapedDao<String, UserHistory> dao;
	
	public UserHistoryRepository() {
		dao = new UserHistoryMapedDao();
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IRepository#addElement()
	 */
	@Override
	public boolean addElement(Object element) {
		if (element instanceof UserHistory) {
			UserHistory userHistory = (UserHistory) element;
			
			return dao.put(userHistory.getUsername(), userHistory);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IRepository#findElement()
	 */
	@Override
	public Object findElementByUsername(String username) {
		return dao.get(username);
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.repositories.IRepository#findAllElements()
	 */
	@Override
	public Object findAllElements() {
		Iterator<UserHistory> daoAsIterator = dao.getMap().values().iterator();
		
		List<UserHistory> userHistoryList = new ArrayList<>();
		
		while (daoAsIterator.hasNext()) {
			userHistoryList.add(daoAsIterator.next());
		}
		
		return (Object) userHistoryList;
	}
}
