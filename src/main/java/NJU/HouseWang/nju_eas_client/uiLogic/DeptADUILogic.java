package NJU.HouseWang.nju_eas_client.uiLogic;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TPDeptVO;

public class DeptADUILogic {
	private String deptName = null;
	private String currentTerm = null;

	public DeptADUILogic() {
		showDept();
		showCurrentTerm();
	}

	/**
	 * 初始化网络服务
	 * 
	 * @return 网络服务
	 */
	public NetService initNetService() {
		ClientPool cPool = ClientPool.getInstance();
		return cPool.getClient();
	}

	public void showDept() {
		String command = "show；self_dept";
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			deptName = ns.receiveFeedback();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * 显示当前学期
	 */
	public void showCurrentTerm() {
		String command = "show；term";
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			currentTerm = ns.receiveFeedback();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object showTPHead_import() {
		String line = null;
		String cmd = "show；teachingplan_head_import";
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
	public Object showTPContent() {
		String command = "show；teachingplan；" + deptName;
		ArrayList<String> list = null;
		String[][] content = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			list = client.receiveList();
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
	 * 得到文件
	 * 
	 * @return 处理结果
	 */
	public Feedback downloadTPFile() {
		String command = "download；teachingplan_file；" + deptName;
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
	 * 得到当前教学计划的状态
	 * 
	 * @return 状态或反馈
	 */
	public Object showTPStatus() {
		String command = "show；teachingplan_status；" + deptName;
		String line = null;
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			line = ns.receiveFeedback();
			ns.shutDownConnection();
			if (line.contains("_")) {
				return Feedback.OPERATION_SUCCEED;
			} else {
				TPDeptVO info = new TPDeptVO(line);
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	/**
	 * 删除教学计划
	 * 
	 * @return 操作结果
	 */
	public Feedback delTP() {
		String command = "del；teachingplan；" + deptName;
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

	public Object showCourseListHead() {
		String cmd = "show；course_list_head";
		String line = null;
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

	public Object showCourseListContent(String grade) {
		String command = "show；course_list；" + currentTerm + "；" + grade + "，"
				+ deptName;
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

	public Object showStudentListHead() {
		String cmd = "show；student_list_head";
		String line = null;
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

	public Object showStudentList(String grade) {
		String command = "show；student_list；" + grade + "，" + deptName;
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
}