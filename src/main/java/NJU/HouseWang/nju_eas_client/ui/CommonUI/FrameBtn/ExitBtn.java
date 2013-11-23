package NJU.HouseWang.nju_eas_client.ui.CommonUI.FrameBtn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitBtn extends FrameBtn {
	public ExitBtn() {
		super();
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		setLocation(788, 30);
		this.updateUI();
	}

	public ExitBtn(int x, int y) {
		super();
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		setLocation(x, y);
		this.updateUI();
	}
}
