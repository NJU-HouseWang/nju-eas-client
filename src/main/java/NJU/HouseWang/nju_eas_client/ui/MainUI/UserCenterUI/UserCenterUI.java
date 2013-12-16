/*
 * 文件名：UserCenterUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-30
 * 最后修改：王鑫
 * 修改时间：
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.UserCenterUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.SmallMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.SmallCommonFrame;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

/*
 * 类：UserCenterUI
 * 
 */
public class UserCenterUI {
	private static int FUNC_NUM = 2;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "查看信息", "修改密码" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
	private JPanel[] switchPane = new JPanel[FUNC_NUM];

	public UserCenterUI() {
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

		switchPane[0] = new ShowSelfInfoPanel();
		switchPane[1] = new ChangePasswordPanel();

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
			cardP.add(switchPane[i], funcName[i]);
		}

		menuBtn[0].setSelected(true);
		cardL.show(cardP, funcName[0]);
		frame.add(cardP);
		frame.add(menuBar);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new UserCenterUI();
	}
}
