package NJU.HouseWang.nju_eas_client.IntegrateTest;

import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.uiLogic.LoginUILogic;

public class LoginUIDriver {
	private LoginUILogic loginui = new LoginUILogic(){
		public NetService initNetService() {
			MockClient c = new MockClient();
			try {
				c.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return c;
		}
	};
	
	public static void main(String[] args) {
		LoginUIDriver driver = new LoginUIDriver();
		driver.runTest();
	}
	
	public void runTest() {
		testLogin();
	}
	
	public void testLogin() {
		loginui.login("test", "Test", "test".toCharArray());
		loginui.login("test", "Admin", "test".toCharArray());
		loginui.login("test", "SchoolDean", "test".toCharArray());
		loginui.login("test", "DeptAD", "test".toCharArray());
		loginui.login("test", "Teacher", "test".toCharArray());
		loginui.login("test", "Student", "test".toCharArray());
	}
}
