package NJU.HouseWang.nju_eas_client.uiController;

import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.ui.LoginUI.LoginUI;

public class LoginUIController {
	private NetService client = new Client("192.168.0.107", 9001);
	private LoginUI ui = null;
	private String command = null;
	private Feedback feedback = null;

	public LoginUIController() {
		ui = new LoginUI(this);
		ui.create();
	}

	public void login(UserType userType, String userName, char[] password) {
		command = "Login；" + userType.toString() + "；" + userName + "；"
				+ new String(password);
		System.out.println(command);
		try {
			client.createConnection();
			client.sendCommand(command);
			feedback = Feedback.valueOf(client.receiveCommand());
			System.out.println(feedback);
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new LoginUIController();
	}
}