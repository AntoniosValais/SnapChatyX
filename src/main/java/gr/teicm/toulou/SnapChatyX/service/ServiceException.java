package gr.teicm.toulou.SnapChatyX.service;

/**
 * 
 * 
 * @since Dec 24, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		
		super();
		
	}
	
	public ServiceException(final String message) {
		
		super(message);
		
	}
	
	public ServiceException(final String message, final Throwable cause) {
		
		super(message, cause);
		
	}
	
	public ServiceException(final Throwable cause) {
		
		super(cause);
		
	}
	
}
