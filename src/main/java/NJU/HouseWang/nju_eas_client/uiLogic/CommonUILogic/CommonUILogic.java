package NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic;

import java.io.IOException;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class CommonUILogic {

	private ClientPool cPool = null;

	protected NetService initNetService() {
		cPool = ClientPool.getInstance();
		return cPool.getClient();
	}

	public Feedback logout() {
		NetService client = initNetService();
		String line = null;
		try {
			client.sendCommand("logout");
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (IOException e) {
		}
		return Feedback.valueOf(line);
	}
}
