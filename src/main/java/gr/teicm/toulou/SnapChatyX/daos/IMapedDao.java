package gr.teicm.toulou.SnapChatyX.daos;

import java.util.Map;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 * 
 * @param <K> - the key type
 * @param <E> - the entry type
 */
public interface IMapedDao<K, E> {
	
	boolean isExistingEntry(K key);
	
	boolean put(K key, E entry);
	
	E get(K key);
	
	boolean update(K key, E entry);
	
	boolean delete(K key, E entry);
	
	Map<K, E> getMap();
	
}
