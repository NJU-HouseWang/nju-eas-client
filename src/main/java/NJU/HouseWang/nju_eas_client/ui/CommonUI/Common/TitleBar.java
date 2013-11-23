package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class TitleBar extends JPanel {
	private CommonLabel welcomel = null;
	private ClickedLabel namel = null;
	private ClickedLabel msgBoxl = null;
	private ClickedLabel logoutl = null;

	public TitleBar(String userName) {
		welcomel = new CommonLabel("Welcome!");
		namel = new ClickedLabel(userName);
		msgBoxl = new ClickedLabel("消息盒子");
		logoutl = new ClickedLabel("注销");

		setBounds(860 - 30 - 220, 78, 220, 20);
		setBackground(Color.getHSBColor(0, 0, (float) 0.16));
		FlowLayout fl = (FlowLayout) getLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		fl.setVgap(0);
		fl.setHgap(10);

		// namel.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseReleased(MouseEvent arg0) {
		// new UserCenterUI().create();
		// }
		// });
		// msgBoxl.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseReleased(MouseEvent arg0) {
		// new MsgBoxUI().create();
		// }
		// });

		add(welcomel);
		add(namel);
		add(msgBoxl);
		add(logoutl);
	}

}
