package gr.teicm.toulou.SnapChatyX.dao;

/**
 * 
 * 
 * @since Dec 4, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class DataAccessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public DataAccessException() {
		
		super();
		
	}
	
	public DataAccessException(final String message) {
		
		super(message);
		
	}
	
	public DataAccessException(final String message, final Throwable cause) {
		
		super(message, cause);
		
	}
	
	public DataAccessException(final Throwable cause) {
		
		super(cause);
		
	}
	
}
