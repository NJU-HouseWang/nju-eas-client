package NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private JButton btn = new JButton();
	private JLabel lb = new JLabel("修改密码", JLabel.CENTER);

	public ChangePasswordPanel() {
		setLayout(null);
		picPanel.setSize(700, 425);
		picPanel.setBounds(0, 0, 700, 425);

		oldpf.setBounds(228, 124, 240, 30);
		newpf1.setBounds(228, 168, 240, 30);
		newpf2.setBounds(228, 212, 240, 30);

		btn.setIcon(new ImageIcon("img/CommonIcon/ChangePasswordBtn_0.png"));
		btn.setRolloverIcon(new ImageIcon(
				"img/CommonIcon/ChangePasswordBtn_1.png"));
		btn.setSelectedIcon(new ImageIcon(
				"img/CommonIcon/ChangePasswordBtn_2.png"));

		btn.setBorderPainted(false);
		btn.setLayout(new BorderLayout());
		btn.setBounds(339, 256, 130, 35);
		lb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lb.setForeground(Color.white);
		btn.add(lb, BorderLayout.CENTER);

		add(oldpf);
		add(newpf1);
		add(newpf2);
		add(btn);
		add(picPanel);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pw1 = newpf1.getPassword();
				char[] pw2 = newpf2.getPassword();
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
