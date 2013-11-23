package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

import NJU.HouseWang.nju_eas_client.systemMessage.UserType;

public class UserTypeBox extends JComboBox<UserType> {
	public UserTypeBox() {
		super();
		setBounds(149, 239, 243, 30);
		setFont(new Font("微软雅黑", Font.BOLD, 15));
		setForeground(Color.gray);
	}
}
