/*
 * 文件名：SchoolDeanUI.java
 * 创建者：王晔
 * 创建时间：2013-10-18
 * 最后修改：王鑫
 * 修改时间：2013-10-30
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.BigMenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.MenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.HomeMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.MenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.CommonFrame;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

/*
 * 类：SchoolDeanUI
 * 
 */
public class SchoolDeanUI {
	private final int FUNC_NUM = 5;
	private final String[] FUNC_BTN_NAME = { "homeBtn", "edufwBtn", "tpBtn",
			"infoBtn", "commoncouBtn" };
	private final String[] FUNC_BTN_TEXT = {"主页","管理教学框架","管理教学计划","查看信息列表","管理通识课程"};
	private CommonFrame frame = new CommonFrame("SchoolFrame");
	private TitleBar tbar = null;
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[FUNC_NUM];
	private JPanel[] childp = new JPanel[FUNC_NUM];
	private JPanel switchPane = new JPanel();
	private CardLayout mcl = new CardLayout();

	public SchoolDeanUI(String userName) {
		tbar = new TitleBar(userName,frame);

		childp[0] = new HomePanel();
		childp[1] = new EduFrameworkPanel();
		childp[2] = new TeachingPlanPanel();
		childp[3] = new InfoListPanel();
		childp[4] = new CommonCoursePanel();

		switchPane.setSize(1000, 590);
		switchPane.setLocation(30, 140);
		switchPane.setLayout(mcl);

		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i],FUNC_BTN_TEXT[i]);
			mbar.add(mBtn[i]);
			childp[i].setSize(800, 490);
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
