package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RefreshBtn extends JButton {
	private String path = "img/CommonIcon/";
	private String name = "RefreshBtn";
	private String suffix = ".png";

	public RefreshBtn() {
		super();
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_1" + suffix));
		setSize(24, 24);
		setPreferredSize(new Dimension(24,24));
		setBorderPainted(false);
		updateUI();
	}
}
