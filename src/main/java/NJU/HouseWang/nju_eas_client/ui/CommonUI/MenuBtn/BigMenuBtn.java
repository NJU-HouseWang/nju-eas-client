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
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(254, 94);
		setPreferredSize(new Dimension(254, 94));
		setBorderPainted(false);
		updateUI();
	}
}
