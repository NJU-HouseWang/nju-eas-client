package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

import NJU.HouseWang.nju_eas_client.systemMessage.UserType;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

public class UserTypeBox extends JComboBox<UserTypeVO> {
	public UserTypeBox() {
		super();
		setBounds(149, 239, 243, 30);
		setFont(new Font("微软雅黑", Font.BOLD, 15));
		setForeground(Color.gray);

		addItem(new UserTypeVO(null, "请选择用户类型..."));
		addItem(new UserTypeVO(UserType.Admin.toString(),
				UserType.Admin.getChName()));
		addItem(new UserTypeVO(UserType.SchoolDean.toString(),
				UserType.SchoolDean.getChName()));
		addItem(new UserTypeVO(UserType.DeptAD.toString(),
				UserType.DeptAD.getChName()));
		addItem(new UserTypeVO(UserType.Teacher.toString(),
				UserType.Teacher.getChName()));
		addItem(new UserTypeVO(UserType.Student.toString(),
				UserType.Student.getChName()));
	}

}
