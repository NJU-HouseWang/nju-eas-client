package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public class EditItemUILogic {
	public Feedback editUser(String itemName, String itemInfo) {
		String command = "edit；" + itemName + "；" + itemInfo;
		try {
			ClientPool cPool = ClientPool.getInstance();
			NetService net = cPool.getClient();
			net.sendCommand(command);
			return Feedback.valueOf(net.receiveFeedback());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}
}
