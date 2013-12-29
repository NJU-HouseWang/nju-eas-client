package NJU.HouseWang.nju_eas_client.uiLogic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.MessageVO;

public class MsgBoxUILogic {
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
	 * 从服务器上获取表内容
	 * 
	 * @param listName
	 *            列表名
	 * @return 如果失败则返回Feedback类型，如果成功返回String[][]类型
	 */
	public ArrayList<MessageVO> showMsgList(int type) {
		String command = "show；message_list；" + type;
		ArrayList<MessageVO> list = new ArrayList<MessageVO>();
		ArrayList<String> l = new ArrayList<String>();
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			l = client.receiveList();
			client.shutDownConnection();
			for (String s : l) {
				MessageVO m = new MessageVO(s);
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Feedback sendMsg(MessageVO msg) {
		String command = "send；message；" + msg.toCommand();
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Feedback.valueOf(line);
	}

	public Feedback saveDraft(MessageVO msg) {
		String command = "save；draft；" + msg.toCommand();
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Feedback.valueOf(line);
	}

	public Feedback delMessage(int type, String id) {
		String command = "del；message；" + type + "；" + id;
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Feedback.valueOf(line);
	}

	public Feedback eraseMessage(int type, String id) {
		String command = "erase；message；" + type + "；" + id;
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Feedback.valueOf(line);
	}

	public String showUserName(String uid) {
		String command = "show；user_name；" + uid;
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}
}
