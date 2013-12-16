package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.Launcher;
import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class LoginUILogic {
	private ClientPool cPool = null;
	
	protected NetService initNetService() {
		cPool = ClientPool.getInstance();
		NetService client = cPool.getClient();
		return client;
	}

	public Feedback login(String userName, String userType, char[] password) {
		String command = "login；" + userType + "；" + userName + "；"
				+ new String(password);
		Feedback feedback = null;
		try {
			NetService client = initNetService();
			client = initNetService();
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
	
	public Feedback logout() {
		String command = "logout";
		NetService client = initNetService();
		Feedback feedback = null;
		try {
			client.sendCommand(command);
			feedback = Feedback.valueOf(client.receiveFeedback());
			client.shutDownConnection();
		} catch (Exception e) {
			return Feedback.INTERNET_ERROR;
		}
		return feedback;
	}
}
