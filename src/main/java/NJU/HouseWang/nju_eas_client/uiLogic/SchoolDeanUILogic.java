package NJU.HouseWang.nju_eas_client.uiLogic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TPDeptVO;

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

	public Feedback addEduFrameWork(ArrayList<String> list) {
		String cmd = "add；eduframework";
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			client.sendList(list);
			line = client.receiveFeedback();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 从服务器上获取教学框架的表头
	 * 
	 * @return 如果失败则返回Feedback类型，如果成功返回String[]类型
	 */
	public Object showEduFwHead() {
		String line = null;
		String cmd = "show；eduframework_head";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public Object showEduFwHead_Import() {
		String line = null;
		String cmd = "show；eduframework_head_import";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 获取教学框架的内容
	 * 
	 * @return 如果失败则返回Feedback类型，如果成功返回String[][]类型，列表为空则直接返回空列表
	 */
	public Object showEduFwContent() {
		String cmd = "show；eduframework";
		ArrayList<String> list = null;
		String[][] content = null;
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			list = client.receiveList();
			client.shutDownConnection();
			content = new String[list.size()][];
			if (list.isEmpty()) {
				return Feedback.LIST_EMPTY;
			}
			for (int i = 0; i < list.size(); i++) {
				content[i] = list.get(i).split("；");
			}
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].equals("null")) {
						content[i][j] = "";
					} else if (content[i][j].contains("0-0")) {
						content[i][j] = content[i][j].replaceAll("0-0", "");
					}
				}
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 删除当前的教学框架策略
	 * 
	 * @return 处理结果
	 */
	public Feedback delEdufw() {
		String command = "del；eduframework";
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
		String cmd = "show；teachingplan_head";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
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
			if (list.isEmpty()) {
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
	 * 显示教学计划列表
	 * 
	 * @return 教学计划列表或者错误反馈
	 */
	public Object showTPList() {
		String command = "show；teachingplan_list";
		ArrayList<String> list = null;
		ArrayList<TPDeptVO> l = new ArrayList<TPDeptVO>();
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			list = ns.receiveList();
			ns.shutDownConnection();
			for (String s : list) {
				TPDeptVO dept = new TPDeptVO();
				dept.deptName = s.split("；")[0];
				dept.isCommitted = Boolean.valueOf(s.split("；")[1]);
				dept.tpState = Integer.parseInt(s.split("；")[2]);
				l.add(dept);
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 显示信息列表头
	 * 
	 * @param listName
	 *            列表名
	 * @return 列表头的数组或是错误反馈
	 */
	public Object showInfoListHead(String listName) {
		String line = null;
		String cmd = "show；" + listName + "_head";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 显示信息列表
	 * 
	 * @param listName
	 * @param conditions
	 * @return
	 */
	public Object showInfoList(String listName, String conditions) {
		String cmd = "show；" + listName + "；" + conditions;
		ArrayList<String> list = null;
		String[][] content = null;
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			list = client.receiveList();
			client.shutDownConnection();
			content = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				content[i] = list.get(i).split("；");
			}
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].equals("null")) {
						content[i][j] = "";
					}
				}
			}
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 新增通识课
	 * 
	 * @param itemName
	 *            项目名
	 * @param itemInfo
	 *            新的项目信息
	 * @return 处理结果
	 */
	public Feedback addCommonCourse(String info) {
		String command = "add；common_course；" + info;
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
	 * 编辑通识课
	 * 
	 * @param itemInfo
	 *            新的项目信息
	 * @return 处理结果
	 */
	public Feedback editCommonCourse(String itemInfo) {
		String command = "edit；common_course；" + itemInfo;
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
	 * 删除通识课
	 * 
	 * @param id
	 *            课程ID
	 * @return 处理结果
	 */
	public Feedback delCommonCourse(String id) {
		String command = "del；common_course；" + id;
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

	public Object showCourseEditHead() {
		String line = null;
		String cmd = "show；common_course_list_head_edit";
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public Object showCourseEdit(String id) {
		String line = null;
		String cmd = "show；common_course_edit；" + id;
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			if (!line.contains("；")) {
				return Feedback.valueOf(line);
			} else {
				String[] head = line.split("；");
				return head;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public String showTeacherName(String id) {
		String line = null;
		String cmd = "show；teacher_name；" + id;
		try {
			NetService client = initNetService();
			client.sendCommand(cmd);
			line = client.receiveFeedback();
			client.shutDownConnection();
			return line;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
