package NJU.HouseWang.nju_eas_client.uiController;

import NJU.HouseWang.nju_eas_client.launcher.ClientLauncher;
import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.ui.LoginUI.LoginUI;

public class LoginUIController {
	private NetService client = new Client("localhost", 9001);
	private LoginUI ui = null;
	private String command = null;
	private String feedback = null;

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
			feedback = client.receiveCommand();
			System.out.println(feedback);
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (feedback == null) {
			ui.showFeedback(-5);
		} else {
			switch (feedback) {
			case "0":
				ClientLauncher.createUI(userType.toString(), userName);
				ui.dispose();
				break;
			default:
				ui.showFeedback(-4);
			}
		}
	}

	public static void main(String[] args) {
		new LoginUIController();
	}
}