package NJU.HouseWang.nju_eas_client.uiLogic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class StudentUILogic {
	private String currentTerm = null;

	public StudentUILogic() {
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

	public Object showCourseListHead() {
		String line = null;
		String cmd = "show；course_list_head";
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

	public Object showMyCourseList() {
		String command = "show；stu_course_list；";
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

	public Object showCommonCourseListHead() {
		String line = null;
		String cmd = "show；common_course_list_head";
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

	public Object showYixuanCommonCourseList() {
		String command = "show；yixuan_common_course_list；";
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

	public Object showSelectedCommonCourseListHead() {
		String line = null;
		String cmd = "show；selected_common_course_list";
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

	public Object showSelectedCommonCourseList() {
		String command = "show；selected_common_course_list；";
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
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
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

	public Feedback selectCommonCourse(String courseId) {
		String command = "select；common_course；" + courseId;
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

	public Feedback byelectCommonCourse(String courseId) {
		String command = "byelect；common_course；" + courseId;
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

	public Feedback quitCommonCourse(String courseId) {
		String command = "quit；common_course；" + courseId;
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

	public Object showScoreListHead() {
		String line = null;
		String cmd = "show；student_score_list_head";
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

	public Object showScoreList(String term) {
		String command = "show；student_score_list；" + term;
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
}
