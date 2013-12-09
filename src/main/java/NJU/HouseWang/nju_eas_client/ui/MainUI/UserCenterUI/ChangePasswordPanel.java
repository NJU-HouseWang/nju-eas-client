package NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.PasswordField;

public class ChangePasswordPanel extends JPanel {

	private CommonPanel picPanel = new CommonPanel("changePasswordPanel");

	private PasswordField oldpf = new PasswordField("请输入原密码...");
	private PasswordField newpf1 = new PasswordField("请输入新密码...");
	private PasswordField newpf2 = new PasswordField("请再次输入新密码...");
	private JButton btn = new JButton();
	private JLabel lb = new JLabel("修改密码",JLabel.CENTER);

	public ChangePasswordPanel() {
		setLayout(null);
		picPanel.setSize(700, 425);
		picPanel.setBounds(0, 0, 700, 425);

		oldpf.setBounds(228, 124, 240, 30);
		newpf1.setBounds(228, 168, 240, 30);
		newpf2.setBounds(228, 212, 240, 30);

		btn.setIcon(new ImageIcon("img/CommonIcon/ChangePasswordBtn_0.png"));
		btn.setRolloverIcon(new ImageIcon("img/CommonIcon/ChangePasswordBtn_1.png"));
		btn.setSelectedIcon(new ImageIcon("img/CommonIcon/ChangePasswordBtn_2.png"));

		btn.setBorderPainted(false);
		btn.setLayout(new BorderLayout());
		btn.setBounds(339, 256, 130, 35);
		lb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lb.setForeground(Color.white);
		btn.add(lb,BorderLayout.CENTER);

		add(oldpf);
		add(newpf1);
		add(newpf2);
		add(btn);
		add(picPanel);
	}
}
