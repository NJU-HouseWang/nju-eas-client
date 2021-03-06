package NJU.HouseWang.nju_eas_client.ui.CommonUI.Field;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField {
	private String info = "请输入密码...";

	public PasswordField(String origin) {
		this.info = origin;
		create();
	}

	public PasswordField() {
		create();
	}

	public void create() {
		setBounds(198, 366, 238, 30);
		setBorder(null);
		setFont(new Font("微软雅黑", Font.PLAIN, 15));
		setForeground(Color.DARK_GRAY);
		setEchoChar('\0');
		setText(info);

		addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (new String(getPassword()).equals("")) {
					setText(info);
					setEchoChar('\0');
				}
			}

			public void focusGained(FocusEvent arg0) {
				if (new String(getPassword()).equals(info)) {
					setText("");
					setEchoChar('●');
				}
			}
		});
	}
}
