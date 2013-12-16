package NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TPDeptVO;

public class CommonUILogic {

	private ClientPool cPool = null;

	protected NetService initNetService() {
		cPool = ClientPool.getInstance();
		return cPool.getClient();
	}

	public Feedback logout() {
		NetService client = new Client();
		String line = null;
		try {
			client.createConnection();
			client.sendCommand("logout");
			line = client.receiveFeedback();
			client.shutDownConnection();
		} catch (IOException e) {
		}
		if (line != null) {
			return Feedback.valueOf(line);
		} else {
			return null;
		}
	}

	/**
	 * 显示年级列表
	 * 
	 * @return 年级列表或错误反馈
	 */
	public ArrayList<String> getGradeList() {
		String command = "show；grade_list";
		ArrayList<String> l = new ArrayList<String>();
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			l = ns.receiveList();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 显示学期列表
	 * 
	 * @return 学期列表或错误反馈
	 */
	public ArrayList<String> getTermList() {
		String command = "show；term_list";
		ArrayList<String> l = new ArrayList<String>();
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			l = ns.receiveList();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 显示院系列表
	 * 
	 * @return 院系列表或错误反馈
	 */
	public ArrayList<String> getDeptList() {
		String command = "show；dept_list";
		ArrayList<String> l = new ArrayList<String>();
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			l = ns.receiveList();
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * 显示TP院系列表
	 * 
	 * @return TP院系列表或错误反馈
	 */
	public ArrayList<TPDeptVO> getTPDeptList() {
		String command = "show；teachingplan_list";
		ArrayList<String> list = null;
		ArrayList<TPDeptVO> l = new ArrayList<TPDeptVO>();
		try {
			NetService ns = initNetService();
			ns.sendCommand(command);
			list = ns.receiveList();
			ns.shutDownConnection();
			for (String s : list) {
				TPDeptVO dept = new TPDeptVO(s);
				l.add(dept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
}
