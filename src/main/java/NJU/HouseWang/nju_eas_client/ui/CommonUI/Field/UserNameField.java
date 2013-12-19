package NJU.HouseWang.nju_eas_client.ui.CommonUI.Field;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class UserNameField extends JTextField {
	static String info = "请输入用户名...";

	public UserNameField() {
		super(info);
		setBounds(152, 283, 238, 30);
		setBorder(null);
		setFont(new Font("微软雅黑", Font.PLAIN, 15));
		setForeground(Color.DARK_GRAY);
		addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {
				if (getText().equals("")) {
					setText(info);
				}
			}

			public void focusGained(FocusEvent arg0) {
				if (getText().equals(info)) {
					setText("");
				}
			}
		});
	}
}
