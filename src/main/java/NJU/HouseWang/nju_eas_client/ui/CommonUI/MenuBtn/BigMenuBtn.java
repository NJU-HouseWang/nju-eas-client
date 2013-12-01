package NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BigMenuBtn extends JButton {
	private String path = "img/BigMenuIcon/";
	private String name = null;
	private String suffix = ".png";

	public BigMenuBtn(String btnName) {
		this.name = btnName;
		setName(btnName);
		setIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_0" + suffix)));
		setRolloverIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_1" + suffix)));
		setPressedIcon(new ImageIcon(ClassLoader.getSystemResource(path + name + "_2" + suffix)));
		setSize(100, 100);
		setPreferredSize(new Dimension(100, 100));
		setBorderPainted(false);
		updateUI();
	}
}
