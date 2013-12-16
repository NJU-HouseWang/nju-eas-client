package NJU.HouseWang.nju_eas_client.IntegrateTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public class MockClient implements NetService{
	private String cmd = null;
	private File file = null;
	private String feedback = null;

	public void sendCommand(String cmd) throws Exception {
		System.out.println("发送命令： " + cmd);
		this.cmd = cmd;
	}


	public void sendFile(File file) throws Exception {
		System.out.println("发送文件： " + file);
	}


	public String receiveFeedback() throws IOException {
		switch(cmd) {
		case "login；Test；test；test":
		case "login；Admin；test；test":
		case "login；SchoolDean；test；test":
		case "login；DeptAD；test；test":
		case "login；Teacher；test；test":
		case "login；Student；test；test":
			feedback = Feedback.OPERATION_SUCCEED.toString();
			break;
		}
		System.out.println("收到反饋： " + feedback);
		return feedback;
	}


	public File receiveFile() throws Exception {

		return null;
	}


	public void sendList(ArrayList<String> list) throws IOException {
		for (String str : list) {
			System.out.println("Send List Item :" + str);
		}
	}


	public ArrayList<String> receiveList() throws Exception {

		return null;
	}


	public void createConnection() throws Exception {
		System.out.println("创建连接");
	}


	public void shutDownConnection() throws Exception {
		System.out.println("关闭连接");
	}
}
