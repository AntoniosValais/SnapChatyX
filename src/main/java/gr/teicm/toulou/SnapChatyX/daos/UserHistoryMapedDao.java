package gr.teicm.toulou.SnapChatyX.daos;

import java.util.HashMap;
import java.util.Map;

import gr.teicm.toulou.SnapChatyX.model.UserHistory;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 * 
 */
public class UserHistoryMapedDao implements IMapedDao<String, UserHistory> {
	
	private final Map<String, UserHistory> map;
	
	public UserHistoryMapedDao(Map<String, UserHistory> map) {
		this.map = map;
	}
	
	public UserHistoryMapedDao() {
		this.map = new HashMap<>();
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean put(String key, UserHistory entry) {
		if (!map.containsKey(key)) {
			map.put(key, entry);
			
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#get(java.lang.Object)
	 */
	@Override
	public UserHistory get(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#update(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean update(String key, UserHistory entry) {
		return map.replace(key, map.get(key), entry);
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#delete(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean delete(String key, UserHistory entry) {
		return map.remove(key, entry);
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#getAll()
	 */
	@Override
	public Map<String, UserHistory> getMap() {
		return map;
	}

}
