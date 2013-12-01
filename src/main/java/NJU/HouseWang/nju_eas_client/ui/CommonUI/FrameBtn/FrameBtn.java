package NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FrameBtn extends JButton {
	private String path = "img/FrameIcon/";
	private String name = this.getClass().getSimpleName();
	private String suffix = ".png";

	public FrameBtn() {
		setIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_0" + suffix)));
		setRolloverIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_1" + suffix)));
		setPressedIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_2" + suffix)));
		setSize(36, 25);
		setBorderPainted(false);
	}
}
