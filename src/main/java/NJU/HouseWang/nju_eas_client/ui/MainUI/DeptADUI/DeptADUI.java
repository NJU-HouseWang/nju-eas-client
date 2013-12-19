package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

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

public class DeptADUI {
	private static int FUNC_NUM = 4;
	private static String[] FUNC_BTN_NAME = { "homeBtn", "tpBtn", "deptCouBtn",
			"deptStuBtn" };
	private static String[] FUNC_BTN_TEXT = { "主页", "管理教学计划", "管理学院课程",
			"管理学院学生" };

	private CommonFrame frame = new CommonFrame("DeptFrame");
	private TitleBar tbar = null;
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[4];
	private JPanel[] childp = new JPanel[4];
	private JPanel switchPane = new JPanel();
	private CardLayout mcl = new CardLayout();

	public DeptADUI(String userName) {
		tbar = new TitleBar(userName, frame);

		childp[0] = new HomePanel();
		childp[1] = new TeachingPlanPanel();
		childp[2] = new DeptCoursePanel();
		childp[3] = new DeptStudentPanel();

		switchPane.setSize(800, 490);
		switchPane.setLocation(30, 140);
		switchPane.setLayout(mcl);

		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i], FUNC_BTN_TEXT[i]);
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

	private void setListener() {
		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mcl.show(switchPane, ((MenuBtn) e.getSource()).getName());
				}
			});
		}
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(null, feedback.getContent());
	}

	public void showFeedback(String feedback) {
		JOptionPane.showMessageDialog(null, feedback);
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

	public static void main(String[] args) {
		new DeptADUI("王东霞");
	}
}
