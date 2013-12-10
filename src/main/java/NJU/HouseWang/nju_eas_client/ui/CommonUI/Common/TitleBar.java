package NJU.HouseWang.nju_eas_client.ui.CommonUI.Common;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.net.Client;
import NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI.MsgBoxUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI.UserCenterUI;

public class TitleBar extends JPanel {
	private CommonLabel welcomel = null;
	private ClickedLabel namel = null;
	private ClickedLabel msgBoxl = null;
	private ClickedLabel logoutl = null;

	// 0: 管理员和学校教务老师
	// 1: 其他成员
	public TitleBar(String userName) {
		welcomel = new CommonLabel("Welcome!");
		namel = new ClickedLabel(userName);
		msgBoxl = new ClickedLabel("消息盒子");
		logoutl = new ClickedLabel("注销");
		namel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserCenterUI();
			}
		});
		msgBoxl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MsgBoxUI();
			}
		});
		logoutl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Client client = new Client();
					client.createConnection();
					client.sendCommand("logout");
					client.receiveFeedback();
					client.shutDownConnection();
				} catch (Exception e1) {
				}
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
