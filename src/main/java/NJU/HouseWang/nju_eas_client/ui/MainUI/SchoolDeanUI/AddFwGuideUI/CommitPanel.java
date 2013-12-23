package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class CommitPanel extends JPanel {
	private SchoolDeanUILogic slogic = new SchoolDeanUILogic();
	private DefaultTableModel dtm = new DefaultTableModel();
	private CommonTable table = new CommonTable(dtm);
	private JScrollPane scrollPanel = new JScrollPane(table);

	private String[] head = null;
	private String[][] content = null;

	private ArrayList<String> l = null;

	public CommitPanel(ArrayList<String> courseInfo) {
		this.l = courseInfo;
		Object o = slogic.showEduFwHead_Import();
		if (o instanceof String[]) {
			head = (String[]) slogic.showEduFwHead_Import();
		}
		setBackground(Color.white);
		setLayout(null);

		scrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel.setBounds(5, 5, 615, 300);

		dtm.setColumnIdentifiers(head);

		add(scrollPanel);
		showTable();
	}

	public void showTable() {
		content = new String[l.size()][l.get(0).split("；").length];
		for (int i = 0; i < l.size(); i++) {
			content[i] = l.get(i).split("；");
		}
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < content[i].length; j++) {
				if (content[i][j].equals("null")) {
					content[i][j] = "";
				}
			}
		}
		dtm.setDataVector(content, head);
	}

	public Feedback uploadEduFw(JButton nextbtn, JButton cancelbtn,
			WaitingLabel lb) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < table.getRowCount(); i++) {
			String s = "";
			for (int j = 0; j < head.length; j++) {
				String tmp = (String) dtm.getValueAt(i, j);
				if (tmp == null || tmp.equals("")) {
					tmp = "null";
				}
				s += tmp + "；";
			}
			list.add(s);
		}
		Feedback fb = slogic.addEduFrameWork(list);
		JOptionPane.showMessageDialog(null, fb.getContent());
		cancelbtn.setEnabled(true);
		nextbtn.setVisible(true);
		lb.setVisible(false);
		return fb;
	}
}
