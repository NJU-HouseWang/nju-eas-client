package NJU.HouseWang.nju_eas_client.ui.MainUI.MsgBoxUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.MsgMenuBtn;

public class MsgPanel extends JPanel {
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
		cardP.setBounds(151, 1, 548, 423);
		cardP.setLayout(cardL);
		functionPanel.setBounds(0, 1, 150, 423);
		functionPanel.setBackground(Color.getHSBColor(0, 0, (float) 0.68));
		FlowLayout fl = (FlowLayout) functionPanel.getLayout();
		fl.setVgap(0);
		
		switchPane[0] = new MsgInPanel();
		switchPane[1] = new MsgOutPanel();
		switchPane[2] = new MsgDraftPanel();
		switchPane[3] = new MsgTrashPanel();
		
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
			cardP.add(switchPane[i], funcName[i]);
		}


		msgBtn[0].setSelected(true);
		add(functionPanel);
		add(cardP);
	}
}
