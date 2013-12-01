/*
 * 文件名：LoginUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-13
 * 最后修改：王鑫
 * 修改时间：2013-11-02
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import NJU.HouseWang.nju_eas_client.Launcher;
import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.PasswordField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserNameField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserTypeBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CommonBtn.LoginBtn;
import NJU.HouseWang.nju_eas_client.uiService.UIService;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

/*
 * 类：LoginUI
 * 
 */
public class LoginFrame extends CommonFrame implements UIService {
	private NetService client;
	private LoginBtn loginBtn = null;
	private UserTypeBox utBox = null;
	private UserNameField unField = null;
	private PasswordField pwField = null;

	public LoginFrame() {
		super("LoginFrame");
		client = new Client();
		utBox = new UserTypeBox();
		unField = new UserNameField();
		pwField = new PasswordField();
		loginBtn = new LoginBtn();
		add(loginBtn);
		add(utBox);
		add(unField);
		add(pwField);
		setVisible(true);
		setListener();

	}

	private void setListener() {
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ea) {
				if (utBox.getSelectedIndex() == 0) {
					showFeedback(Feedback.USERTYPE_EMPTY);
				} else if (unField.getText().equals("")
						|| unField.getText().equals("请输入用户名...")) {
					showFeedback(Feedback.USERNAME_EMPTY);
				} else if (pwField.getPassword().length == 0
						|| pwField.getPassword()[0] == '请') {
					showFeedback(Feedback.PASSWORD_EMPTY);
				} else {
					login(((UserTypeVO) utBox.getSelectedItem()).name_en,
							unField.getText(), pwField.getPassword());
				}
			}
		});
	}

	public void setClient(NetService client) {
		this.client = client;
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}

	public Feedback login(String userType, String userName, char[] password) {
		String command = "login；" + userType + "；" + userName + "；"
				+ new String(password);
		Feedback feedback = null;
		try {
			client.createConnection();
			client.sendCommand(command);
			feedback = Feedback.valueOf(client.receiveFeedback());
		} catch (Exception e) {
			showFeedback(Feedback.INTERNET_ERROR);
			e.printStackTrace();
		}
		if (feedback == Feedback.OPERATION_SUCCEED) {
			dispose();
			Launcher.createUI(userType, userName);
		} else {
			showFeedback(feedback);
		}
		return feedback;
	}
}
