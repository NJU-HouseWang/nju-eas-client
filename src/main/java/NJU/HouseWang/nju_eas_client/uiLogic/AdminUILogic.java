package NJU.HouseWang.nju_eas_client.uiLogic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TermVO;

/**
 * 管理员界面所对应的逻辑类，负责与网络层交互
 * 
 * @author 王鑫
 * @version 2013/12/03
 */
public class AdminUILogic {

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
		if (!(listName.equals("student_list") || listName.equals("login_list") || listName
				.equals("log_list"))) {
			listName = "teacher_list";
		}
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
	public Object showTableContent(String listName, String conditions) {
		if (!(listName.equals("student_list") || listName.equals("login_list") || listName
				.equals("log_list"))) {
			listName = "teacher_list；" + listName.split("_")[0];
		}
		String command2 = "show；" + listName + "；" + conditions;
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
	 * 删除用户
	 * 
	 * @param itemName
	 *            项目名
	 * @param itemInfo
	 *            项目信息
	 * @return 服务器反馈
	 */
	public Feedback delUser(String itemName, String id) {
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

	/**
	 * 增加用户
	 * 
	 * @param itemName
	 *            项目名
	 * @param itemInfo
	 *            项目信息
	 * @return 操作结果
	 */
	public Feedback addUser(String itemName, String itemInfo) {
		String command = "add；" + itemName + "；" + itemInfo;
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

	/**
	 * 修改用户
	 * 
	 * @param itemName
	 *            项目名
	 * @param itemInfo
	 *            项目信息
	 * @return 操作结果
	 */
	public Feedback editUser(String itemName, String itemInfo) {
		String command = "edit；" + itemName + "；" + itemInfo;
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

	/**
	 * 显示当前学期
	 * 
	 * @return 学期信息或反馈
	 */
	public Object showCurrentTerm() {
		String command = "show；term";
		String line = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			line = ns.receiveFeedback();
			ns.shutDownConnection();
			if (line.contains("_")) {
				return Feedback.valueOf(line);
			} else if (line.contains("-")) {
				return new TermVO(line);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 创建新学期
	 * 
	 * @param itemInfo
	 *            学期信息，例如：2012-2013年 第2学期
	 * @return 操作结果
	 */
	public Feedback createNewTerm(String itemInfo) {
		String command = "edit；term；" + itemInfo;
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

	/**
	 * 更改系统状态
	 * 
	 * @param statesName
	 *            状态名
	 * @param states
	 *            状态
	 * @return
	 */
	public Feedback swicthStates(String statesName, String states) {
		String command = "edit；status；" + statesName + "；" + states;
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

	/**
	 * 开始抽签
	 * 
	 * @return 操作结果
	 */
	public Feedback processSelection() {
		String command = "process；common_course_selection";
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

	/**
	 * 显示系统状态
	 * 
	 * @param statesName
	 *            状态名称
	 * @return 状态或者操作反馈
	 */
	public Object showStates(String statesName) {
		String command = "show；status；" + statesName;
		String line = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			line = ns.receiveFeedback();
			ns.shutDownConnection();
			if (line.contains("_")) {
				return Feedback.valueOf(line);
			} else if (line.contains("false") || line.contains("true")) {
				return Boolean.valueOf(line.split("；")[1]);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}
}
