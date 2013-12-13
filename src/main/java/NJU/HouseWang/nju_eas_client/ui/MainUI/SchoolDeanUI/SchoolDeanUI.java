/*
 * 文件名：SchoolDeanUI.java
 * 创建者：王晔
 * 创建时间：2013-10-18
 * 最后修改：王鑫
 * 修改时间：2013-10-30
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.BigMenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.MenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.BigMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.MenuBtn;

/*
 * 类：SchoolDeanUI
 * 
 */
public class SchoolDeanUI {
	private static int FUNC_NUM = 5;
	private static String[] FUNC_BTN_NAME = { "homeBtn", "edufwBtn", "tpBtn",
			"infoBtn", "commoncouBtn" };

	private CommonFrame frame = new CommonFrame("SchoolFrame");
	private TitleBar tbar = null;
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[FUNC_NUM];
	private JPanel[] childp = new JPanel[FUNC_NUM];
	private JPanel switchPane = new JPanel();
	private CardLayout mcl = new CardLayout();

	public SchoolDeanUI(String userName) {
		tbar = new TitleBar(userName);

		childp[0] = new HomePanel();
		childp[1] = new EduFrameworkPanel();
		childp[2] = new TeachingPlanPanel();
		childp[3] = new InfoListPanel();
		childp[4] = new CommonCoursePanel();

		switchPane.setSize(800, 480);
		switchPane.setLocation(30, 150);
		switchPane.setLayout(mcl);

		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i]);
			mbar.add(mBtn[i]);
			childp[i].setSize(800, 480);
			switchPane.add(childp[i], FUNC_BTN_NAME[i]);
		}

		mbar.setLocation(30, 100);
		mcl.show(switchPane, FUNC_BTN_NAME[0]);
		mBtn[0].setSelected(true);
		frame.add(switchPane);
		frame.add(mbar);
		frame.add(tbar);
		setListener();
		frame.setVisible(true);
	}

	public void setListener() {
		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mcl.show(switchPane, ((MenuBtn) e.getSource()).getName());
				}
			});
		}
	}

	public void showFeedback(String feedback) {
		JOptionPane.showMessageDialog(frame, feedback);
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	public static void main(String[] args) {
		new SchoolDeanUI("陈建群");
	}

	@SuppressWarnings("serial")
	class HomePanel extends JPanel {
		private BigMenuBtn[] bmbtn = new BigMenuBtn[FUNC_NUM - 1];
		private BigMenuBar bmbar = new BigMenuBar();

		public HomePanel() {
			for (int i = 0; i < bmbtn.length; i++) {
				bmbtn[i] = new BigMenuBtn(FUNC_BTN_NAME[i + 1]);
				bmbar.add(bmbtn[i]);
			}
			bmbar.setLocation(70, 50);
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
