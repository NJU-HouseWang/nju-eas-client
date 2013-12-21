package NJU.HouseWang.nju_eas_client.ui.MainUI.ExportUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.SmallMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Frame.SmallCommonFrame;

public class ExportUI {
	private int FUNC_NUM = 1;
	private SmallCommonFrame frame = null;
	private JPanel menuBar = null;
	private ButtonGroup btnG = null;
	private CardLayout cardL = null;
	private JPanel cardP = null;
	private String[] funcName = { "导出" };
	private SmallMenuBtn[] menuBtn = new SmallMenuBtn[FUNC_NUM];
	private JPanel[] switchPane = new JPanel[FUNC_NUM];

	public ExportUI(String[] head, String[][] content) {
		frame = new SmallCommonFrame("IOMgrFrame");
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

		switchPane[0] = new ExportPanel(head, content);
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
}
