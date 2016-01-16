package gr.teicm.toulou.SnapChatyX.userAccountServlet;


import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.InterfaceDataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.SnapClient;

/*
*
* @Author AntoniosValais
*
*/
public class UserAccountController implements InterfaceUserAccountController
{
	private InterfaceDataAccessObject dataAccessObject;

	private SnapClient user;
	
	private InterfaceUserAccount userAccount;
	
	public UserAccountController()
	{
		dataAccessObject = DataAccessObject.DAO;
	}
	
	@Override
	public InterfaceUserAccount getUserAccount( String username )
	{
		user = dataAccessObject.getRegisteredSnapClientWithUsername( username );
		
		if( user == null )
		{
			return null;
		}
		else
		{
			userAccount = new UserAccount( user );
			
			return userAccount;
		}
	}	
}
