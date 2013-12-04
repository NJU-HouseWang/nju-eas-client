/*
 * 文件名：MsgBoxUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-30
 * 最后修改：王鑫
 * 修改时间：
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SmallCommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.MsgMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.SmallMenuBtn;

/*
 * 类：MsgBoxUI
 * 
 */
public class MsgBoxUI {
	private static int FUNC_NUM = 2;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "公告", "私信" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
	private int index = 0;
	private JPanel[] switchPane = new JPanel[FUNC_NUM];

	public MsgBoxUI() {
		frame = new SmallCommonFrame("MsgBoxFrame");
		menuBar = new JPanel();
		cardL = new CardLayout();
		cardP = new JPanel(cardL);
		cardP.setBounds(30, 105, 700, 425);
		btnG = new ButtonGroup();
		menuBar.setBounds(30, 60, 700, 45);
		menuBar.setBackground(Color.getHSBColor((float) 0.617, (float) 1,
				(float) 0.24));

		FlowLayout lf = (FlowLayout) menuBar.getLayout();
		lf.setHgap(0);
		lf.setVgap(0);
		lf.setAlignment(FlowLayout.LEFT);

		switchPane[0] = new MsgPanel();
		switchPane[1] = new JPanel();
		for (int i = 0; i < FUNC_NUM; i++) {
			menuBtn[i] = new SmallMenuBtn(funcName[i]);
			menuBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cardL.show(cardP,
							((SmallMenuBtn) e.getSource()).getFuncName());
				}
			});
			btnG.add(menuBtn[i]);
			menuBar.add(menuBtn[i]);
			// switchPane[i] = new JPanel();
			// JLabel bl = new JLabel(funcName[i] + "面板");
			// bl.setFont(new Font("微软雅黑", Font.BOLD, 30));
			// switchPane[i].add(bl);
			cardP.add(switchPane[i], funcName[i]);
		}

		// 设置第一个Panel的内容
		// 设置第二个Panel的内容

		menuBtn[0].setSelected(true);
		cardL.show(cardP, funcName[0]);
		frame.add(cardP);
		frame.add(menuBar);
		frame.setVisible(true);
	}

	public void showFeedback(Feedback feedback) {

	}

	class MsgPanel extends JPanel {
		private int FUNC_NUM = 4;
		private ButtonGroup btnG = new ButtonGroup();
		private JPanel functionPanel = new JPanel();
		private JPanel cardP = new JPanel();
		private CardLayout cardL = new CardLayout();
		private String[] funcName = { "收件箱", "发件箱", "草稿箱", "回收站" };
		private JPanel[] switchPane = new JPanel[FUNC_NUM];
		private MsgMenuBtn[] msgBtn = new MsgMenuBtn[FUNC_NUM];

		public MsgPanel() {
			setLayout(null);
			cardP.setBounds(200, 1, 500, 423);
			cardP.setLayout(cardL);
			functionPanel.setBounds(0, 1, 200, 423);
			functionPanel.setBackground(Color.getHSBColor(0, 0, (float) 0.68));
			FlowLayout fl = (FlowLayout) functionPanel.getLayout();
			fl.setVgap(0);
			for (int i = 0; i < FUNC_NUM; i++) {
				msgBtn[i] = new MsgMenuBtn(funcName[i]);
				btnG.add(msgBtn[i]);
				msgBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cardL.show(cardP,
								((MsgMenuBtn) e.getSource()).getFuncName());
					}
				});
				functionPanel.add(msgBtn[i]);

				switchPane[i] = new JPanel();
				JLabel bl = new JLabel(funcName[i] + "面板");
				bl.setFont(new Font("微软雅黑", Font.BOLD, 30));
				switchPane[i].add(bl);
				cardP.add(switchPane[i], funcName[i]);
			}
			msgBtn[0].setSelected(true);
			add(functionPanel);
			add(cardP);
		}
	}
}
