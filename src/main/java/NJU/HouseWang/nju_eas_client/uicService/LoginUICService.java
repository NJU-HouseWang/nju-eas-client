package NJU.HouseWang.nju_eas_client.uicService;

import NJU.HouseWang.nju_eas_client.systemMessage.UserType;

public interface LoginUICService extends UICService {

	public void login(UserType userType, String userName, char[] password);

}
