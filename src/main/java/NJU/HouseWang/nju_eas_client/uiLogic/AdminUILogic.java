package NJU.HouseWang.nju_eas_client.uiLogic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogicService.DelItemService;
import NJU.HouseWang.nju_eas_client.uiLogicService.ShowTableService;

/**
 * 管理员界面所对应的逻辑类，负责与网络层交互
 * 
 * @author 王鑫
 * @version 2013/12/03
 */
public class AdminUILogic implements ShowTableService, DelItemService {

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
	 * 从服务器上获取表头
	 * 
	 * @param listName
	 *            列表名
	 * @return 如果失败则返回Feedback类型，如果成功返回String[]类型
	 */
	public Object showTableHead(String listName) {
		String line = null;
		String[] head = null;
		String cmd = "show；" + listName + "_head";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			head = line.split("；");
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return head;
	}

	/**
	 * 从服务器上获取表内容
	 * 
	 * @param listName
	 *            列表名
	 * @return 如果失败则返回Feedback类型，如果成功返回String[][]类型
	 */
	public Object showTableContent(String listName) {
		String command2 = "show；" + listName;
		ArrayList<String> list = null;
		String[][] content = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command2);
			list = client.receiveList();
			content = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				content[i] = list.get(i).split("；");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return content;
	}

	/**
	 * 删除项目
	 * 
	 * @param itemName
	 *            项目名
	 * @param itemInfo
	 *            项目信息
	 * @return 服务器反馈
	 */
	public Feedback delItem(String itemName, String id) {
		String command = "del；" + itemName + "；" + id;
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
