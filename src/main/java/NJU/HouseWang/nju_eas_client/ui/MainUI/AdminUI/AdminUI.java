package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.BigMenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.MenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.HomeMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.MenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.CommonFrame;

public class AdminUI {
	private static int FUNC_NUM = 4;
	private static String[] FUNC_BTN_NAME = { "homeBtn", "userBtn",
			"statesBtn", "logBtn" };
	private static String[] FUNC_BTN_TEXT = { "主页", "管理系统用户", "管理系统状态",
			"查看系统日志" };
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[FUNC_NUM];
	private CardLayout mcl = new CardLayout();
	private JPanel switchPane = new JPanel(mcl);
	private JPanel[] mPanel = new JPanel[FUNC_NUM];
	private CommonFrame frame = new CommonFrame("AdminFrame");
	private TitleBar tbar = null;

	public AdminUI(String userName) {
		tbar = new TitleBar(userName, frame);
		mbar.setLocation(30, 100);
		switchPane.setBounds(30, 140, 1000, 590);
		mPanel[0] = new HomePanel();
		mPanel[1] = new UserPanel();
		mPanel[2] = new StatesPanel();
		mPanel[3] = new LogPanel();
		for (int i = 0; i < FUNC_NUM; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i], FUNC_BTN_TEXT[i]);
			mbar.add(mBtn[i]);
			switchPane.add(mPanel[i], FUNC_BTN_NAME[i]);
			final int index = i;
			mBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mcl.show(switchPane, ((MenuBtn) e.getSource()).getName());
					if (index == 3) {
						((LogPanel) mPanel[index]).init();
					}
				}
			});
		}
		mBtn[0].setSelected(true);

		frame.add(switchPane);
		frame.add(mbar);
		frame.add(tbar);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AdminUI("Admin");
	}

	@SuppressWarnings("serial")
	class HomePanel extends JPanel {
		private HomeMenuBtn[] bmbtn = new HomeMenuBtn[FUNC_NUM - 1];
		private BigMenuBar bmbar = new BigMenuBar();

		public HomePanel() {
			for (int i = 0; i < bmbtn.length; i++) {
				bmbtn[i] = new HomeMenuBtn(FUNC_BTN_NAME[i + 1]);
				bmbar.add(bmbtn[i]);
			}
			((FlowLayout) getLayout()).setAlignment(FlowLayout.CENTER);
			setLayout(new BorderLayout());
			add(bmbar,BorderLayout.CENTER);
			setListener();
		}

		void setListener() {
			for (int i = 0; i < FUNC_BTN_NAME.length - 1; i++) {
				bmbtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String bname = ((HomeMenuBtn) e.getSource()).getName();
						mcl.show(switchPane, bname);
						for (int i = 1; i < FUNC_BTN_NAME.length; i++) {
							if (FUNC_BTN_NAME[i].equals(bname)) {
								mBtn[i].setSelected(true);
							}
						}
					}
				});
			}
		}
	}
}
