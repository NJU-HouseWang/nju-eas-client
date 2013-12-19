package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class MenuBtn extends JToggleButton {

	public MenuBtn(String btnName,String btnText) {
		setName(btnName);

		if (btnName.equals("homeBtn")) {
			setSize(40, 40);
			setPreferredSize(new Dimension(40, 40));
			setIcon(new ImageIcon("img/CommonIcon/HomeBtn_0.png"));
			setRolloverIcon(new ImageIcon("img/CommonIcon/HomeBtn_1.png"));
			setSelectedIcon(new ImageIcon("img/CommonIcon/HomeBtn_1.png"));
		} else {
			setText(btnText);
			setSize(120, 40);
			setPreferredSize(new Dimension(120, 40));
			setIcon(new ImageIcon("img/CommonIcon/MenuBtn_0.png"));
			setRolloverIcon(new ImageIcon("img/CommonIcon/MenuBtn_1.png"));
			setSelectedIcon(new ImageIcon("img/CommonIcon/MenuBtn_1.png"));
			JLabel lb = new JLabel(btnText, JLabel.CENTER);
			lb.setFont(new Font("微软雅黑", Font.BOLD, 14));
			lb.setForeground(Color.white);
			this.setLayout(new BorderLayout());
			this.add(lb, BorderLayout.CENTER);
		}
		setBorderPainted(false);
		updateUI();
	}
}
