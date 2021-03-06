package NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.Launcher;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.ClickedLabel;
import NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI.MsgBoxUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI.UserCenterUI;
import NJU.HouseWang.nju_eas_client.uiLogic.CommonUILogic;

public class TitleBar extends JPanel {
	private CommonUILogic logic = new CommonUILogic();
	private ClickedLabel namel = null;
	private ClickedLabel msgBoxl = null;
	private ClickedLabel logoutl = null;

	public TitleBar(String userName, final JFrame frame) {
		namel = new ClickedLabel(userName);
		namel.setForeground(Color.WHITE);
		msgBoxl = new ClickedLabel("消息盒子");
		msgBoxl.setForeground(Color.WHITE);
		logoutl = new ClickedLabel("注销");
		logoutl.setForeground(Color.WHITE);
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
					frame.setVisible(false);
					frame.dispose();
					logic.logout();
					Launcher.createUI("Login", null);
				} catch (Exception e1) {
					frame.setVisible(false);
					frame.dispose();
					Launcher.createUI("Login", null);
				}
			}
		});

		setBounds(775, 78, 250, 20);
		setBackground(Color.getHSBColor(0, 0, (float) 0.16));
		FlowLayout fl = (FlowLayout) getLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		fl.setVgap(0);
		fl.setHgap(10);

		add(namel);
		add(msgBoxl);
		add(logoutl);
	}

}
