package NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn;

import javax.swing.JFrame;

public class MinBtn extends FrameBtn {
	JFrame frame = null;

	public MinBtn(JFrame f) {
		super();
		frame = f;
		setLocation(751, 30);
		updateUI();
	}

	public MinBtn(JFrame f, int x, int y) {
		super();
		frame = f;
		setLocation(x, y);
		updateUI();
	}
}
