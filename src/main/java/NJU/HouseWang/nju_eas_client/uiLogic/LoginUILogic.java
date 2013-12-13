package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.Launcher;
import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogicService.LoginService;

public class LoginUILogic implements LoginService {

	public NetService initNetService() throws Exception {
		NetService client = new Client();
		client.createConnection();
		return client;
	}

	@Override
	public Feedback login(String userName, String userType, char[] password) {
		String command = "login；" + userType + "；" + userName + "；"
				+ new String(password);
		Feedback feedback = null;
		try {
			NetService client = initNetService();
			client = initNetService();
			client.createConnection();
			client.sendCommand(command);
			feedback = Feedback.valueOf(client.receiveFeedback());
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		if (feedback == Feedback.OPERATION_SUCCEED) {
			Launcher.createUI(userType, userName);
		}
		return feedback;
	}
}
