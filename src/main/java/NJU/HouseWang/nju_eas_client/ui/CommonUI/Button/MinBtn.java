package NJU.HouseWang.nju_eas_client.ui.CommonUI.Button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MinBtn extends JButton {
	private JFrame frame = null;
	private String path = "img/FrameIcon/";
	private String name = "MinBtn";
	private String suffix = ".png";

	public MinBtn(JFrame f) {
		super();
		frame = f;
		setLocation(943, 30);
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(36, 25);
		setBorderPainted(false);
		setFocusable(false);
		setListener();
		updateUI();
	}

	public MinBtn(JFrame f, int x, int y) {
		super();
		frame = f;
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
				if (frame != null) {
					frame.setExtendedState(JFrame.ICONIFIED);
				}
			}
		});
	}
}
