/*
 * 文件名：ClientLauncher.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-10-18
 */
package NJU.HouseWang.nju_eas_client.launcher;

import NJU.HouseWang.nju_eas_client.uiController.AdminUIController;
import NJU.HouseWang.nju_eas_client.uiController.LoginUIController;

/*
 * 类：ClientLauncher
 * 
 */
public class ClientLauncher {

	public static void main(String[] args) {
		createUI("Login", null);
	}

	public static void createUI(String type, String userName) {
		switch (type) {
		case "Login":
			new LoginUIController();
			break;
		case "Admin":
			new AdminUIController(userName);
			break;
//		case "School":
//			new SchoolDeanUI().create();
//			break;
//		case "Dept":
//			new DeptADUI().create();
//			break;
//		case "Teacher":
//			new TeacherUI().create();
//			break;
		}
	}
}
