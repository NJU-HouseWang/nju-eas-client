/*
 * 文件名：LoginUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-13
 * 最后修改：王鑫
 * 修改时间：2013-11-02
 */
package NJU.HouseWang.nju_eas_client.ui.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.PasswordField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserNameField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserTypeBox;
import NJU.HouseWang.nju_eas_client.uiController.LoginUIController;
import NJU.HouseWang.nju_eas_client.uiService.UIService;

/*
 * 类：LoginUI
 * 
 */
public class LoginUI implements UIService {

	private CommonFrame frame = null;
	private LoginBtn loginBtn = null;
	private LoginUIController uic = null;
	private UserTypeBox utBox = null;
	private UserNameField unField = null;
	private PasswordField pwField = null;

	public LoginUI(LoginUIController uic) {
		this.uic = uic;
	}

	public void create() {
		frame = new CommonFrame("LoginFrame");
		utBox = new UserTypeBox();
		unField = new UserNameField();
		pwField = new PasswordField();
		loginBtn = new LoginBtn();
		initUserTypeBox();
		frame.add(loginBtn);
		frame.add(utBox);
		frame.add(unField);
		frame.add(pwField);
		frame.setVisible(true);

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
					uic.login((UserType) utBox.getSelectedItem(),
							unField.getText(), pwField.getPassword());
				}
			}
		});
	}

	public void dispose() {
		frame.setVisible(false);
		frame.dispose();
		uic = null;
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	private void initUserTypeBox() {
		utBox.addItem(UserType.Null);
		utBox.addItem(UserType.Admin);
	}
}
