package gr.teicm.toulou.SnapChatyX.repositories;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 * 
 * @param <T> - the type of repository
 */
public interface IRepository {
	
	enum RepositoryType {
		USER_HISTORY
	}
	
	boolean addElement(Object element);
	
	Object findElementByUsername(String identifier);
	
	Object findAllElements();
	
}
