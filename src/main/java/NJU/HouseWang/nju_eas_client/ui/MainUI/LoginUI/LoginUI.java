/*
 * 文件名：LoginUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-13
 * 最后修改：王鑫
 * 修改时间：2013-12-3
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.LoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.PasswordField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserNameField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.UserTypeBox;
import NJU.HouseWang.nju_eas_client.uiLogic.LoginUILogic;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

public class LoginUI {
	private CommonFrame frame = null;
	private LoginBtn loginBtn = null;
	private UserTypeBox utBox = null;
	private UserNameField unField = null;
	private PasswordField pwField = null;
	private LoginUILogic logic = null;

	public LoginUI() {
		frame = new CommonFrame("LoginFrame");
		logic = new LoginUILogic();
		utBox = new UserTypeBox();
		unField = new UserNameField();
		pwField = new PasswordField();
		loginBtn = new LoginBtn();
		frame.add(loginBtn);
		frame.add(utBox);
		frame.add(unField);
		frame.add(pwField);
		frame.setVisible(true);
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
					Feedback fb = logic.login(unField.getText(),
							((UserTypeVO) utBox.getSelectedItem()).name_en,
							pwField.getPassword());
					if (fb == Feedback.OPERATION_SUCCEED) {
						frame.dispose();
					}
				}
			}
		});
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	public void showFeedback(String feedback) {
		JOptionPane.showMessageDialog(frame, feedback);
	}
}
