package NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Field.PasswordField;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.CommonPanel;
import NJU.HouseWang.nju_eas_client.uiLogic.UserCenterUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ChangePasswordPanel extends JPanel {
	private UserCenterUILogic logic = new UserCenterUILogic();
	private CommonPanel picPanel = new CommonPanel("changePasswordPanel");

	private PasswordField oldpf = new PasswordField("请输入原密码...");
	private PasswordField newpf1 = new PasswordField("请输入新密码...");
	private PasswordField newpf2 = new PasswordField("请再次输入新密码...");
	private CommonBtn btn = new CommonBtn("修改密码");

	public ChangePasswordPanel() {
		setLayout(null);
		picPanel.setSize(700, 425);
		picPanel.setBounds(0, 0, 700, 425);

		oldpf.setBounds(228, 124, 240, 30);
		newpf1.setBounds(228, 168, 240, 30);
		newpf2.setBounds(228, 212, 240, 30);

		btn.setBounds(339, 256, 130, 35);

		add(oldpf);
		add(newpf1);
		add(newpf2);
		add(btn);
		add(picPanel);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pw1 = newpf1.getPassword();
				char[] pw2 = newpf2.getPassword();
				if (pw1[0] == '请' || pw2[0] == '请'
						|| oldpf.getPassword()[0] == '请') {
					JOptionPane.showMessageDialog(null, "请输入完整的密码信息");
				}
				boolean judge = true;
				judge = (pw1.length == pw2.length);
				if (judge) {
					for (int i = 0; i < pw1.length; i++) {
						if (pw1[i] != pw2[i]) {
							judge = false;
						}
					}
				}
				if (judge) {
					Feedback fb = logic.changePassword(oldpf.getPassword(), pw1);
					JOptionPane.showMessageDialog(null, fb.getContent());
					oldpf.setText("");
					newpf1.setText("");
					newpf2.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "两次输入的新密码不一致");
				}
			}
		});
	}
}
