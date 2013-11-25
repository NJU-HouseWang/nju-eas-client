package NJU.HouseWang.nju_eas_client.uiController;

import NJU.HouseWang.nju_eas_client.launcher.ClientLauncher;
import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.ui.LoginUI.LoginUI;
import NJU.HouseWang.nju_eas_client.uicService.LoginUICService;

public class LoginUIController implements LoginUICService {
	private ClientPool cPool = null;
	private LoginUI ui = null;
	private String command = null;
	private Feedback feedback = null;

	public LoginUIController() {
		cPool = ClientPool.getInstance();
		try {
			cPool.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ui = new LoginUI(this);
		ui.create();
	}

	public void login(UserType userType, String userName, char[] password) {
		command = "login；" + userType.toString() + "；" + userName + "；"
				+ new String(password);
		System.out.println(command);
		try {
			NetService ns = cPool.getClient();
			ns.createConnection();
			ns.sendCommand(command);
			feedback = Feedback.valueOf(ns.receiveCommand());
			ui.showFeedback(feedback);
		} catch (Exception e) {
			ui.showFeedback(Feedback.INTERNET_ERROR);
			e.printStackTrace();
		}
		if (feedback == Feedback.OPERATION_SUCCEED) {
			ClientLauncher.createUI(userType.toString(), userName);
			ui.dispose();
		} else {
			ui.showFeedback(feedback);
		}
	}
}