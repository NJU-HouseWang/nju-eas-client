/*
 * 文件名：Launcher.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-12-1
 */
package NJU.HouseWang.nju_eas_client;

import javax.swing.JOptionPane;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.ui.MainUI.AdminFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.LoginFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.StudentFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherFrame;

/*
 * 类：Launcher
 * 
 */
public class Launcher {

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
			case "School":
				new SchoolDeanFrame(userName);
				break;
			case "Dept":
				new DeptADFrame(userName);
				break;
			case "Teacher":
				new TeacherFrame(userName);
				break;
			case "Student":
				new StudentFrame(userName);
				break;
			default:
				System.err.println("Error:不存在的界面");
			}
		}
	}
}
