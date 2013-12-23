package NJU.HouseWang.nju_eas_client;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI.AdminUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI.DeptADUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.LoginUI.LoginUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.SchoolDeanUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI.StudentUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI.TeacherUI;

/**
 * 程序入口
 * 
 * @author Xin
 * @version 2013-12-21
 */
public class Launcher {

	/**
	 * 主方法
	 * 
	 * @param args
	 *            运行时参数
	 */
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e) {
//		}
		createUI("Login", null);
	}

	/**
	 * 创建界面
	 * 
	 * @param type
	 *            用户类型
	 * @param userName
	 *            用户名
	 */
	public static void createUI(String type, String userName) {
		if (type.equals("Login")) {
			new LoginUI();
		} else {
			switch (type) {
			case "Admin":
				new AdminUI(userName);
				break;
			case "SchoolDean":
				new SchoolDeanUI(userName);
				break;
			case "DeptAD":
				new DeptADUI(userName);
				break;
			case "Teacher":
				new TeacherUI(userName);
				break;
			case "Student":
				new StudentUI(userName);
				break;
			default:
				System.err.println("Error:不存在的界面");
			}
		}
	}
}
