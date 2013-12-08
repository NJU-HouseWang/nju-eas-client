package NJU.HouseWang.nju_eas_client.uiLogic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

/**
 * 管理员界面所对应的逻辑类，负责与网络层交互
 * 
 * @author 王鑫
 * @version 2013/12/03
 */
public class SchoolDeanUILogic {

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
	 * 从服务器上获取教学框架的表头
	 * 
	 * @return 如果失败则返回Feedback类型，如果成功返回String[]类型
	 */
	public Object showEduFwHead() {
		String line = null;
		String[] head = null;
		String cmd = "show；edufw_head";
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
	 * 获取教学框架的内容
	 * 
	 * @return 如果失败则返回Feedback类型，如果成功返回String[][]类型
	 */
	public Object showEduFwContent() {
		String command2 = "show；edufw";
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
	 * 删除当前的教学框架策略
	 * 
	 * @return 处理结果
	 */
	public Feedback delEdufw() {
		String command = "del；edufw";
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}
	
	/**
	 * 从服务器上获取教学计划的表头
	 * 
	 * @return 如果失败则返回Feedback类型，如果成功返回String[]类型
	 */
	public Object showTPHead() {
		String line = null;
		String[] head = null;
		String cmd = "show；teachingplan_head";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			head = line.split("；");
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return head;
	}
	
	/**
	 * 从服务器上获取院系教学计划的内容
	 * 
	 * @param deptName
	 *            院系名
	 * @return 如果失败则返回Feedback类型，如果成功返回String[][]类型
	 */
	public Object showTPContent(String deptName) {
		String command = "show；teachingplan；" + deptName;
		ArrayList<String> list = null;
		String[][] content = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			list = client.receiveList();
			if(list.isEmpty()) {
				return Feedback.TEACHINGPLAN_NOT_COMMIT;
			}
			content = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				content[i] = list.get(i).split("；");
			}
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return content;
	}
	
	/**
	 * 审核教学计划
	 * 
	 * @param deptName
	 *            院系名
	 * @param states
	 *            0：未审核；1：通过；2：未通过
	 * @return 处理结果
	 */
	public Feedback auditTP(String deptName, int states) {
		String command = "audit；" + deptName + "；" + states;
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
	 * 得到文件名
	 * 
	 * @param deptName
	 *            院系名
	 * @return 处理结果
	 */
	public Object downloadTPFile(String deptName) {
		String command = "download；teachingplan；" + deptName;
		File file = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			file = ns.receiveFile();
			ns.shutDownConnection();
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Feedback.OPERATION_SUCCEED;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 删除列表
	 * 
	 * @param listName
	 *            列表名
	 * @param table
	 *            删除的列表
	 * @return 处理结果
	 */
	public Feedback delList(String listName, String[][] table) {
		String command = "del；" + listName;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			ns.shutDownConnection();
			return Feedback.valueOf(ns.receiveFeedback());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 编辑项目，此处特指审核教学计划
	 * 
	 * @param listName
	 *            列表名
	 * @param itemInfo
	 *            新的项目信息
	 * @return 处理结果
	 */
	public Feedback editItem(String itemName, String itemInfo) {
		String command = "edit；" + itemName + "；" + itemInfo;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			ns.shutDownConnection();
			return Feedback.valueOf(ns.receiveFeedback());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

}
