package gr.teicm.toulou.SnapChatyX.adminListServlet;

import java.util.List;

public interface InterfaceAdminListController {
	List<String> getAdminList();

	Boolean addAdmin(String username);

	Boolean removeAdmin(String username);
}
