package NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame;

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

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.ExitBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.MinBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.CommonPanel;

public class SmallCommonFrame extends JFrame {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static Point origin = new Point();

	private int width = 760;
	private int hight = 560;
	private String frameName = null;
	private MinBtn minBtn = null;
	private ExitBtn exitBtn = null;

	public SmallCommonFrame(String frameName) {
		this.frameName = frameName;

		setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - hight) / 2 - 30, width, hight);

		setLayout(null);

		setBackgroung();

		setMovable(this);
		minBtn = new MinBtn(this, 651, 30);
		exitBtn = new ExitBtn(688, 30);
		minBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		exitBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				setVisible(false);
			}
		});
		add(minBtn);
		add(exitBtn);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setBackgroung() {
		setContentPane(new CommonPanel(frameName));
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		setUndecorated(true);
//		com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
	}

	private void setMovable(final JFrame frame) {
		String titlePath = "img/Title/";
		JButton title = new JButton(new ImageIcon(titlePath + frameName
				+ ".png"));
		title.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				SmallCommonFrame.origin.x = e.getX();
				SmallCommonFrame.origin.y = e.getY();
			}
		});
		title.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
						- origin.y);
			}
		});
		title.setBounds(30, 30, 620, 30);
		title.setBorderPainted(false);
		add(title);
	}
}
