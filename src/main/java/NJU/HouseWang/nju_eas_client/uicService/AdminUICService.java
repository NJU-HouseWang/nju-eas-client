package NJU.HouseWang.nju_eas_client.uicService;

public interface AdminUICService {

	public void showList(String listName, String conditions);

	public void addUser(String content);

	public void delUser(String listName, String id);

	public void updateUser(String listName, String content);

	public void showListHead(String listName);

	public void showIOMgr();

}
