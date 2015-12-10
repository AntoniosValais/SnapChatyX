package gr.teicm.toulou.SnapChatyX.exceptions;

/**
 * 
 * 
 * @since Dec 4, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class DaoException extends Exception {
	
	private static final long serialVersionUID = 8151621556196624040L;
	
	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DaoException(Throwable cause) {
		super(cause);
	}
	
}
