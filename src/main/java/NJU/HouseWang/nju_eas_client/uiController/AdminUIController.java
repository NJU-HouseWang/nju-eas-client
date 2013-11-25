package NJU.HouseWang.nju_eas_client.uiController;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.AdminUI.AdminUI;
import NJU.HouseWang.nju_eas_client.uicService.AdminUICService;

public class AdminUIController implements AdminUICService {
	private ClientPool cPool = null;
	private String userName = null;
	private AdminUI ui = null;
	private String command = null;
	private Feedback feedback = null;
	private String[] head = null;
	private String[][] content = null;

	public AdminUIController(String userName) {
		this.userName = userName;
		cPool = ClientPool.getInstance();
		ui = new AdminUI(this);
		ui.setName(userName);
		ui.create();
	}

	@Override
	public void showListHead(String listName) {
		ArrayList<String> list = null;
		command = "show；" + listName;
		NetService ns = cPool.getClient();
		try {
			ns.sendCommand(command);
			list = ns.receiveList();
			head = (String[]) list.toArray();
			ui.setFormHead(head);
		} catch (Exception e) {
			e.printStackTrace();
			ui.showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	@Override
	public void showList(String listName, String conditions) {
		ArrayList<String> l = null;
		command = "show；" + listName;
		NetService ns = cPool.getClient();
		try {
			ns.sendCommand(command);
			l = ns.receiveList();
			String[][] content = new String[l.size()][head.length];
			for (int i = 0; i < l.size(); i++) {
				content[i] = l.get(i).split("；");
			}
			ui.setForm(content);
		} catch (Exception e) {
			e.printStackTrace();
			ui.showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	@Override
	public void addUser(String content) {
		command = "add；user；" + content;
		NetService ns = cPool.getClient();
		try {
			ns.sendCommand(command);
			feedback = Feedback.valueOf(ns.receiveCommand());
			ns.shutDownConnection();
			ui.showFeedback(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			ui.showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	@Override
	public void delUser(String listName, String id) {
		command = "del；user；" + content;
		NetService ns = cPool.getClient();
		try {
			ns.sendCommand(command);
			feedback = Feedback.valueOf(ns.receiveCommand());
			ns.shutDownConnection();
			ui.showFeedback(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			ui.showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	@Override
	public void updateUser(String listName, String content) {
		command = "update；user；" + content;
		NetService ns = cPool.getClient();
		try {
			ns.sendCommand(command);
			feedback = Feedback.valueOf(ns.receiveCommand());
			ns.shutDownConnection();
			ui.showFeedback(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			ui.showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	@Override
	public void showIOMgr() {

	}

}
