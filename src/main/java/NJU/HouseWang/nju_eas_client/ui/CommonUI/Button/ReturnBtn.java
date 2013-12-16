package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ReturnBtn extends JButton {
	private JFrame frame = null;
	private String path = "img/FrameIcon/";
	private String name = "ExitBtn";
	private String suffix = ".png";

	public ReturnBtn(JFrame frame) {
		this.frame = frame;
		setLocation(788, 30);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(36, 25);
		setBorderPainted(false);
		setListener();
		updateUI();
	}

	public ReturnBtn(int x, int y, JFrame frame) {
		this.frame = frame;
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
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
