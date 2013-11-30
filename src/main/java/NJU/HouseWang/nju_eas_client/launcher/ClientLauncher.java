/*
 * 文件名：ClientLauncher.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-10-18
 */
package NJU.HouseWang.nju_eas_client.launcher;

import javax.swing.JOptionPane;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.ui.MainUI.AdminFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.LoginFrame;

/*
 * 类：ClientLauncher
 * 
 */
public class ClientLauncher {

	public static void main(String[] args) {
		createUI("Login", null);
	}

	public static void createUI(String type, String userName) {
		if (type.equals("Login")) {
			new LoginFrame();
		} else {
			try {
				ClientPool.getInstance().run();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "由于网络问题，请重新登陆");
				type = "Login";
				new LoginFrame();
				e.printStackTrace();
			}
			switch (type) {
			case "Admin":
				new AdminFrame(userName);
				break;
			// case "School":
			// new SchoolDeanUI().create();
			// break;
			// case "Dept":
			// new DeptADUI().create();
			// break;
			// case "Teacher":
			// new TeacherUI().create();
			// break;
			}
		}
	}
}
