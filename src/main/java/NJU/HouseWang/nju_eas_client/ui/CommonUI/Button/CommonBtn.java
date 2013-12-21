package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CommonBtn extends JButton {
	private JLabel lb = new JLabel("", JLabel.CENTER);

	public CommonBtn(String btnText) {
		lb.setText(btnText);
		setIcon(new ImageIcon("img/CommonIcon/CommonBtn_0.png"));
		setRolloverIcon(new ImageIcon("img/CommonIcon/CommonBtn_1.png"));
		setSelectedIcon(new ImageIcon("img/CommonIcon/CommonBtn_2.png"));

		setBorderPainted(false);
		setLayout(new BorderLayout());
		lb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lb.setForeground(Color.white);
		add(lb, BorderLayout.CENTER);
	}
}
