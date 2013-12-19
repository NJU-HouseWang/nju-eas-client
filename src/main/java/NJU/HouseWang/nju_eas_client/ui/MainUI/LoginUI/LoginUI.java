/*
 * 文件名：LoginUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-13
 * 最后修改：王鑫
 * 修改时间：2013-12-3
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.LoginUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.UserTypeBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Field.PasswordField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Field.UserNameField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.uiLogic.LoginUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

public class LoginUI {
	private CommonFrame frame = new CommonFrame("LoginFrame");
	private LoginBtn loginBtn = new LoginBtn();
	private UserTypeBox utBox = new UserTypeBox();
	private UserNameField unField = new UserNameField();
	private PasswordField pwField = new PasswordField();
	private LoginUILogic logic = new LoginUILogic();

	private JPanel wp = new JPanel(new BorderLayout());
	private WaitingLabel wlb = new WaitingLabel();
	
	public LoginUI() {
		frame.add(loginBtn);
		frame.add(utBox);
		frame.add(unField);
		frame.add(pwField);
		wp.setSize(50,50);
		wp.setPreferredSize(new Dimension(50,50));
		wp.add(wlb,BorderLayout.CENTER);
		wp.setLocation(180, 380);
		frame.add(wp);
		wp.setVisible(false);
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
					processWaiting();
					new Thread(new Runnable() {
						public void run() {
							Feedback fb = logic.login(unField.getText(),
									((UserTypeVO) utBox.getSelectedItem()).name_en,
									pwField.getPassword());
							if (fb == Feedback.OPERATION_SUCCEED) {
								frame.dispose();
							} else {
								stopWaiting();
								showFeedback(fb);
							}
						}
					}).start();
				}
			}
		});
		pwField.addActionListener(new ActionListener() {
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
					processWaiting();
					new Thread(new Runnable() {
						public void run() {
							Feedback fb = logic.login(unField.getText(),
									((UserTypeVO) utBox.getSelectedItem()).name_en,
									pwField.getPassword());
							if (fb == Feedback.OPERATION_SUCCEED) {
								frame.dispose();
							} else {
								stopWaiting();
								showFeedback(fb);
							}
						}
					}).start();
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
	
	public void processWaiting() {
		loginBtn.setEnabled(false);
		wp.setVisible(true);
		frame.repaint();
		frame.validate();
	}
	
	public void stopWaiting() {
		loginBtn.setEnabled(true);
		wp.setVisible(false);
		frame.repaint();
		frame.validate();
	}
}
