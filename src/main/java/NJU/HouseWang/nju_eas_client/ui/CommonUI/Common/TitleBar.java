package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxFrame;
import NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterFrame;

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
		namel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserCenterFrame();
			}
		});
		msgBoxl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MsgBoxFrame();
			}
		});

		setBounds(860 - 30 - 250, 78, 250, 20);
		setBackground(Color.getHSBColor(0, 0, (float) 0.16));
		FlowLayout fl = (FlowLayout) getLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		fl.setVgap(0);
		fl.setHgap(10);

		add(welcomel);
		add(namel);
		add(msgBoxl);
		add(logoutl);
	}

}
