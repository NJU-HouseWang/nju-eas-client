package NJU.HouseWang.nju_eas_client.ui.CommonUI.Label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CommonLabel extends JLabel {
	public CommonLabel(String name) {
		super(name);
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBorder(null);
	}
}
