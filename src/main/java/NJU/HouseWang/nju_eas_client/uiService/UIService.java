package NJU.HouseWang.nju_eas_client.uiService;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public interface UIService {
	public void create();

	public void dispose();

	public void showFeedback(Feedback feedback);
}
