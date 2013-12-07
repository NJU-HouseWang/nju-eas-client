package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.BigMenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.MenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.BigMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.MenuBtn;

public class AdminUI {
	private static int FUNC_NUM = 4;
	private static String[] FUNC_BTN_NAME = { "homeBtn", "userBtn",
			"statesBtn", "logBtn" };
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[FUNC_NUM];
	private CardLayout mcl = new CardLayout();
	private JPanel switchPane = new JPanel(mcl);
	private JPanel[] mPanel = new JPanel[FUNC_NUM];
	private CommonFrame frame = new CommonFrame("AdminFrame");
	private TitleBar tbar = null;

	public AdminUI(String userName) {
		tbar = new TitleBar(userName);
		mbar.setLocation(30, 100);
		switchPane.setBounds(30, 150, 800, 480);
		mPanel[0] = new HomePanel();
		mPanel[1] = new UserPanel();
		mPanel[2] = new JPanel();
		mPanel[3] = new LogPanel();
		for (int i = 0; i < FUNC_NUM; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i]);
			mbar.add(mBtn[i]);
			switchPane.add(mPanel[i], FUNC_BTN_NAME[i]);
			mBtn[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mcl.show(switchPane, ((MenuBtn)e.getSource()).getName());
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
		private BigMenuBtn[] bmbtn = new BigMenuBtn[3];
		private BigMenuBar bmbar = new BigMenuBar();
		
		public HomePanel() {
			for (int i = 0; i < bmbtn.length; i++) {
				bmbtn[i] = new BigMenuBtn(FUNC_BTN_NAME[i + 1]);
				bmbar.add(bmbtn[i]);
			}
			bmbar.setLocation(60, 10);
			setBackground(Color.getHSBColor((float) 0.617, (float) 0.42,
					(float) 0.92));
			((FlowLayout) getLayout()).setAlignment(FlowLayout.CENTER);
			setLayout(null);
			add(bmbar);
			setListener();
		}
		
		void setListener() {
			for (int i = 0; i < FUNC_BTN_NAME.length - 1; i++) {
				bmbtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String bname = ((BigMenuBtn) e.getSource()).getName();
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
