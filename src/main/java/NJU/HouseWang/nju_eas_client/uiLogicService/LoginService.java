package NJU.HouseWang.nju_eas_client.uiLogicService;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;

public interface LoginService {
	public Feedback login(String userName, UserType userType, char[] password);
}
