package gr.teicm.toulou.SnapChatyX.resources;

import gr.teicm.toulou.SnapChatyX.controllers.UserController;

/**
 * 
 * @since Dec 8, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class UserResource {
	
	protected UserController controller;
	
	protected UserResource(UserController controller) {
		this.controller = controller;
	}
	
	public UserController getUserController() {
		return controller;
	}
	
}
