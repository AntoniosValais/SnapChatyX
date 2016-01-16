package gr.teicm.toulou.SnapChatyX.dao;

import java.util.HashMap;
import java.util.Map;

import gr.teicm.toulou.SnapChatyX.model.IUserHistory;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserHistoryMapedDao implements IMapedDao<String, IUserHistory> {
	
	private final Map<String, IUserHistory> map;
	
	public UserHistoryMapedDao(Map<String, IUserHistory> map) {
		
		this.map = map;
		
	}
	
	public UserHistoryMapedDao() {
		
		this.map = new HashMap<>();
		
	}
	
	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.daos.IMapedDao#isExistingEntry(java.lang.Object)
	 */
	@Override
	public boolean isExistingEntry(String key) {
		
		return map.containsKey(key);
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean put(String key, IUserHistory entry) {
		
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
	public IUserHistory get(String key) {
		
		if (map.containsKey(key)) {
			
			return map.get(key);
			
		}
		
		return null;
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#update(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean update(String key, IUserHistory entry) {
		
		return map.replace(key, map.get(key), entry);
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#delete(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean delete(String key, IUserHistory entry) {
		
		return map.remove(key, entry);
		
	}

	/* (non-Javadoc)
	 * @see gr.teicm.toulou.SnapChatyX.dao.IMapedDAO#getAll()
	 */
	@Override
	public Map<String, IUserHistory> getMap() {
		
		return map;
		
	}

}
