package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class TeacherUI {
	private final int FUNC_NUM = 4;
	private final String[] FUNC_BTN_NAME = { "homeBtn", "myCouBtn", "myStuBtn",
			"stuScoreBtn" };
	private final String[] FUNC_BTN_TEXT = { "主页", "管理我的课程", "管理我的学生", "管理学生成绩" };

	private CommonFrame frame = new CommonFrame("TeacherFrame");
	private TitleBar tbar = null;
	private MenuBar mbar = new MenuBar();
	private MenuBtn[] mBtn = new MenuBtn[FUNC_NUM];
	private JPanel[] childp = new JPanel[FUNC_NUM];
	private JPanel switchPane = new JPanel();
	private CardLayout mcl = new CardLayout();

	public TeacherUI(String userName) {
		tbar = new TitleBar(userName, frame);

		childp[0] = new HomePanel();
		childp[1] = new MyCoursePanel();
		childp[2] = new MyStudentPanel();
		childp[3] = new StudentScorePanel();

		switchPane.setSize(1000, 590);
		switchPane.setLocation(30, 140);
		switchPane.setLayout(mcl);

		for (int i = 0; i < mBtn.length; i++) {
			mBtn[i] = new MenuBtn(FUNC_BTN_NAME[i], FUNC_BTN_TEXT[i]);
			mbar.add(mBtn[i]);
			childp[i].setSize(1000, 590);
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
					System.out.println(((MenuBtn) e.getSource()).getName());
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
			((FlowLayout) getLayout()).setAlignment(FlowLayout.CENTER);
			setLayout(new BorderLayout());
			add(bmbar, BorderLayout.CENTER);
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
		new TeacherUI("刘钦");
	}
}
