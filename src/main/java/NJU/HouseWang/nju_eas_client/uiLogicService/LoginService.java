package NJU.HouseWang.nju_eas_client.uiLogicService;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public interface LoginService {
	public Feedback login(String userName, String userType, char[] password);
}
