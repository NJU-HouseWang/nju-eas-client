package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic.CommonUILogic;

public class ExitBtn extends JButton {
	public CommonUILogic logic = new CommonUILogic();
	private String path = "img/FrameIcon/";
	private String name = "ExitBtn";
	private String suffix = ".png";

	public ExitBtn() {
		setLocation(788, 30);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(36, 25);
		setBorderPainted(false);
		setListener();
		updateUI();
	}

	public ExitBtn(int x, int y) {
		setLocation(x, y);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(36, 25);
		setBorderPainted(false);
		setListener();
		updateUI();
	}

	private void setListener() {
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				logic.logout();
				System.exit(0);
			}
		});
	}
}
