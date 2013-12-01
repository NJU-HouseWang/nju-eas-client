package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CommonBtn extends JButton {
	private String path = "img/CommonIcon/";
	private String name = this.getClass().getSimpleName();
	private String suffix = ".png";

	public CommonBtn() {
		setIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_0" + suffix)));
		setRolloverIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_1" + suffix)));
		setPressedIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_2" + suffix)));
		setBorderPainted(false);
	}
}
