package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn.ExitBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn.MinBtn;

public class CommonFrame extends JFrame {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static Point origin = new Point();
	private int width = 860;
	private int hight = 660;
	private String frameName = null;
	private MinBtn minBtn = null;
	private ExitBtn exitBtn = null;

	public CommonFrame(String frameName) {
		this.frameName = frameName;
		setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - hight) / 2 - 30, width, hight);
		setLayout(null);

		setBackgroung();
		setMovable(this);
		minBtn = new MinBtn(this);
		exitBtn = new ExitBtn();
		minBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		exitBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		add(minBtn);
		add(exitBtn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setBackgroung() {
		this.setContentPane(new CommonPanel(frameName));
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		setUndecorated(true);
		com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
	}

	private void setMovable(final JFrame frame) {
		String titlePath = "img/Title/";
		JButton title = new JButton(new ImageIcon(ClassLoader.getSystemResource(titlePath + frameName
				+ ".png")));
		title.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CommonFrame.origin.x = e.getX();
				CommonFrame.origin.y = e.getY();
			}
		});
		title.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
		title.setBounds(30, 30, 550, 70);
		title.setBorderPainted(false);
		add(title);
	}
}
