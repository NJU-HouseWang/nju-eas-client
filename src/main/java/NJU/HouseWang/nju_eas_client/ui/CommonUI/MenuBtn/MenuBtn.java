package NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class MenuBtn extends JToggleButton {
	private String path = "img/MenuIcon/";
	private String name = null;
	private String suffix = ".png";

	public MenuBtn(String btnName) {
		this.name = btnName;
		setName(btnName);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setSelectedIcon(new ImageIcon(path + name + "_1" + suffix));
		setSize(120, 50);
		setPreferredSize(new Dimension(120, 50));
		if (btnName.equals("homeBtn")) {
			setSize(58, 50);
			setPreferredSize(new Dimension(58, 50));
		}
		setBorderPainted(false);
		updateUI();
	}
}
