package gr.teicm.toulou.SnapChatyX.controllers;

import gr.teicm.toulou.SnapChatyX.services.UserService;

/**
 * 
 * 
 * @since Dec 8, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserController {
	
	private final UserService userService;
	
	public UserController() {
		userService = UserService.getInstance();
	}
	
	public UserService getUserService() {
		return userService;
	}
	
}
