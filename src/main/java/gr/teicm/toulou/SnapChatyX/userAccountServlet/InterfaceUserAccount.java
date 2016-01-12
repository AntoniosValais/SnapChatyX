package gr.teicm.toulou.SnapChatyX.userAccountServlet;

import java.util.List;

/*
*
* @Author AntoniosValais
*
*/

public interface InterfaceUserAccount
{

	String getUsername();

	String getFirstName();

	String getLastName();

	String getEmail();

	String getLocationName();

	List< String > getFriendList();

	List< String > getBlackList();
	
}
