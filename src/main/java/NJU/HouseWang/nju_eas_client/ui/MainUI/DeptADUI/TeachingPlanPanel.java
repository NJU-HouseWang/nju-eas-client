package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.EduFrameworkMap;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;

public class TeachingPlanPanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[2];
	private SubPanel tpp = null;
	private SubPanel accessoryp = null;
	private SubPanel localStatuesp = null;
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CTable table = null;

	private String[][] content = new String[][] {
			{ "A", "V", "V", "V", "1", "1", "V" },
			{ "A", "C", "C", "C", "2", "2", "C" },
			{ "A", "C", "C", "C", "3", "3", "C" },
			{ "B", "V", "V", "V", "4", "4", "V" },
			{ "B", "F", "F", "F", "5", "5", "F" }

	};

	private String[] head = new String[] { "as", "sdf", "sf", "sdf", "asdf",
			"adsf", "a" };

	@SuppressWarnings("serial")
	public TeachingPlanPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		tpp = new SubPanel("教学计划  ", 500, 380);
		tpp.setLocation(30, 70);

		accessoryp = new SubPanel("附件", 230, 150);
		accessoryp.setLocation(540, 70);

		localStatuesp = new SubPanel("当前状态", 230, 150);
		localStatuesp.setLocation(540, 230);

		map = new EduFrameworkMap(content);
		dtm = new DefaultTableModel(content.length, content[0].length) {
			public boolean isCellEditable(int indexRow, int indexColumn) {
				return false;
			}
		};
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				dtm.setValueAt(content[i][j], i, j);
			}
		}
		dtm.setColumnIdentifiers(head);

		table = new CTable(map, dtm);
		table.setEnabled(false);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		tpp.getCenterPanel().setLayout(new BorderLayout());
		tpp.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);

		add(tpp);
		add(accessoryp);
		add(localStatuesp);
	}

	private void showTP() {
		head = null;
		content = null;
		Object fb = logic.showEduFwHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showEduFwContent();
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
			}
		}
		dtm.setDataVector(content, head);
		table.updateUI();
	}
}