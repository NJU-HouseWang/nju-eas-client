package NJU.HouseWang.nju_eas_client.ui.MainUI.LoginUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 登陆按钮
 * 
 * @author Xin
 * @version 2013-10-27
 */
public class LoginBtn extends JButton {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登陆按钮构造方法
	 */
	public LoginBtn() {
		super();
		setIcon(new ImageIcon("img/CommonIcon/LoginBtn_0.png"));
		setRolloverIcon(new ImageIcon("img/CommonIcon/LoginBtn_1.png"));
		setSelectedIcon(new ImageIcon("img/CommonIcon/LoginBtn_2.png"));
		setBounds(316, 410, 124, 42);
		setBorderPainted(false);
		setFocusable(false);
		updateUI();
	}
}
