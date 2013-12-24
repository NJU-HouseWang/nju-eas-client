package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI.CreateEduFwUI;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class EduFrameworkPanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[3];
	private SubPanel edufwp = null;
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CardLayout cl = new CardLayout();
	private JPanel cardp = new JPanel(cl);
	private CommonTable table1 = null;
	private JScrollPane js1 = new JScrollPane();
	private CommonTable table2 = null;
	private JScrollPane js2 = new JScrollPane();
	private RefreshBtn refreshBtn = new RefreshBtn();

	private String[][] content = null;

	private String[] head = null;

	public EduFrameworkPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("CreateBtn");
		fBtn[1] = new FunctionBtn("ImportBtn");
		fBtn[2] = new FunctionBtn("DelBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		edufwp = new SubPanel("当前框架策略", 940, 480);
		edufwp.setLocation(30, 70);

		refreshBtn.setBounds(3, 3, 22, 22);
		refreshBtn.setPreferredSize(new Dimension(22, 22));
		edufwp.getTopPanel().add(refreshBtn);
		
		edufwp.getCenterPanel().setLayout(new BorderLayout());
		edufwp.getCenterPanel().add(cardp,BorderLayout.CENTER);
		cardp.add(js1,"1");
		cardp.add(js2,"2");
		showEduFw();
		add(edufwp);

		setListener();
	}

	private void setListener() {
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEduFw();
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateEduFwUI();
			}
		});

		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportEduFrameWorkUI();
			}
		});

		fBtn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.delEdufw();
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});
	}

	private void showEduFw() {
		content = null;
		head = null;

		Object fb = logic.showEduFwHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showEduFwContent();
			if ((fb instanceof Feedback) && fb != Feedback.LIST_EMPTY) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
			}
		}
		if (content == null) {
			fBtn[0].setEnabled(true);
			fBtn[1].setEnabled(true);
			fBtn[2].setEnabled(false);
			DefaultTableModel dtm1 = new DefaultTableModel(1,1);
			dtm1.setValueAt("教学框架策略未提交。", 0, 0);
			table1 = new CommonTable(dtm1);
			js1.setViewportView(table1);
			cl.show(cardp, "1");
		} else {
			fBtn[0].setEnabled(false);
			fBtn[1].setEnabled(false);
			fBtn[2].setEnabled(true);
			map = new EduFrameworkMap(content);
			dtm = new DefaultTableModel(content.length,head.length);
			table2 = new CTable(map, dtm);
			dtm.setColumnIdentifiers(head);
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].contains("null-null")) {
						content[i][j] = content[i][j].replaceAll("null-null", "");
					}
					dtm.setValueAt(content[i][j], i, j);
				}
			}
			js2.setViewportView(table2);
			cl.show(cardp, "2");
		}


		this.repaint();
		this.validate();
	}
}
