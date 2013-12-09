package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogicService.TeacherVO;
import NJU.HouseWang.nju_eas_client.vo.StudentVO;

public class UserCenterUILogic {
	/**
	 * 初始化网络服务
	 * 
	 * @return 网络服务
	 */
	public NetService initNetService() {
		ClientPool cPool = ClientPool.getInstance();
		return cPool.getClient();
	}

	/**
	 * 显示个人信息
	 * 
	 * @return 网络反馈的信息
	 */
	public Object showSelfInformation() {
		String command = "show；selfInformation";
		String line = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			line = ns.receiveFeedback();
			ns.shutDownConnection();
			if (line.contains("；")) {
				String[] sp = line.split("；");
				if (sp.length == 4) {
					return new TeacherVO(sp);
				} else {
					return new StudentVO(sp);
				}
			} else {
				return Feedback.valueOf(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public Feedback changePassword(char[] oldpw, char[] newpw) {
		String command = "changePassword；" + new String(oldpw) + "；"
				+ new String(newpw);
		String line = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			line = ns.receiveFeedback();
			ns.shutDownConnection();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}
}
