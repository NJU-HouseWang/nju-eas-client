/*
 * 文件名：MsgBoxUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-30
 * 最后修改：王鑫
 * 修改时间：
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.SmallMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.SmallCommonFrame;

/*
 * 类：MsgBoxUI
 * 
 */
public class MsgBoxUI {
	private int FUNC_NUM = 1;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "消息" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
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
		for (int i = 0; i < FUNC_NUM; i++) {
			menuBtn[i] = new SmallMenuBtn(funcName[i]);
			menuBtn[i].addActionListener(new ActionListener() {
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
		new MsgBoxUI();
	}
}
