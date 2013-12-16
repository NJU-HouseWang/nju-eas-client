package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class HomeMenuBtn extends JButton {
	private String path = "img/HomeMenuIcon/";
	private String name = null;
	private String suffix = ".png";

	public HomeMenuBtn(String btnName) {
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
