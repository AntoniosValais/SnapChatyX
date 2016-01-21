package gr.teicm.toulou.SnapChatyX.banListServlet;

import java.util.List;

/*
*
* @Author iKetsi
*
*/

public interface InterfaceBanListController {

	List<String> getBanList();

	Boolean unbanUser(String username);

	Boolean banUser(String username);

}
