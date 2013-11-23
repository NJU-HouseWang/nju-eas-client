package NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MinBtn extends FrameBtn {
	JFrame frame = null;

	public MinBtn(JFrame f) {
		super();
		frame = f;
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		setLocation(751, 30);
		updateUI();
	}

	public MinBtn(JFrame f, int x, int y) {
		super();
		frame = f;
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		setLocation(x, y);
		updateUI();
	}
}
