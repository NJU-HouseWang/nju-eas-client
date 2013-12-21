package NJU.HouseWang.nju_eas_client.uiLogic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.CourseDetailVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class TeacherUILogic {
	private String currentTerm = null;

	public TeacherUILogic() {
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
	public String showCurrentTerm() {
		String command = "show；term";
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			currentTerm = ns.receiveFeedback();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentTerm;
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
		String command = "show；tea_course_list";
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

	public Object showStudentListHead() {
		String line = null;
		String cmd = "show；student_list_from_course_head";
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

	public Object showMyStudentList(String term, String couId) {
		String command = "show；student_list_from_course；" + term + "；" + couId;
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

	public Object showCourseDetail(String term, String couId) {
		String command = "show；course_detail；" + term + "；" + couId;
		ArrayList<String> list = null;
		CourseDetailVO course = new CourseDetailVO();
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			list = client.receiveList();
			client.shutDownConnection();
			course.introduction = list.get(0);
			course.book = list.get(1);
			course.syllabus = list.get(2);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return course;
	}

	public Feedback editCourseDetail(String term, String couId,
			CourseDetailVO course) {
		String command = "edit；course_detail；" + term + "；" + couId;
		String line = null;
		ArrayList<String> list = new ArrayList<String>();
		list.add(course.introduction);
		list.add(course.book);
		list.add(course.syllabus);
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			client.sendList(list);
			line = client.receiveFeedback();
			client.shutDownConnection();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public Feedback recordScore(String term, String couId, String[][] table) {
		String command = "record；score；" + term + "；" + couId;
		String line = null;
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < table.length; i++) {
			String l = "";
			for (int j = 0; j < table[i].length; j++) {
				if (j == table[i].length - 1) {
					if (table[i][j].equals("")) {
						table[i][j] = "-1";
					} else {
						try {
							double d = Double.parseDouble(table[i][j]);
							int a = (int) d;
							table[i][j] = a + "";
						} catch (NumberFormatException e) {
							return Feedback.NUM_FORMAT_ERROR;
						}
					}
				}
				l += table[i][j] + "；";
			}
			list.add(l);
		}
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			client.sendList(list);
			line = client.receiveFeedback();
			client.shutDownConnection();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}

	public Object showStudentScoreListHead() {
		String line = null;
		String cmd = "show；course_student_list_head";
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

	public Object showStudentScoreList(String term, String couId) {
		String command = "show；course_student_list；" + term + "；" + couId;
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
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].equals("-1")) {
						content[i][j] = "";
					}
				}
			}
			client.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
		return content;
	}

}
