/*
 * 文件名：UserCenterUI.java
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
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.SmallMenuBtn;
import NJU.HouseWang.nju_eas_client.uiService.UIService;

/*
 * 类：UserCenterUI
 * 
 */
public class UserCenterFrame implements UIService {
	private static int FUNC_NUM = 3;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "查看信息", "修改信息", "修改密码" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
	private int index = 0;
	private JPanel[] switchPane = new JPanel[FUNC_NUM];

	public UserCenterFrame() {
		frame = new SmallCommonFrame("UserCenterFrame");
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
			switchPane[i] = new JPanel();
			JLabel bl = new JLabel(funcName[i] + "面板");
			bl.setFont(new Font("微软雅黑", Font.BOLD, 30));
			switchPane[i].add(bl);
			cardP.add(switchPane[i], funcName[i]);
		}

		// 设置第一个Panel的内容
		// 设置第二个Panel的内容
		// 设置第三个Panel的内容

		menuBtn[0].setSelected(true);
		cardL.show(cardP, funcName[0]);
		frame.add(cardP);
		frame.add(menuBar);
		frame.setVisible(true);
	}

	public void showFeedback(Feedback feedback) {

	}
}
