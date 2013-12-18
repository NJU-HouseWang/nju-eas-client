package NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.TermBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.StudentUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TermVO;

public class MyScorePanel extends JPanel {
	private StudentUILogic logic = new StudentUILogic();
	private SubPanel scorep = new SubPanel("我的成绩列表", 740, 425);
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);
	private TermBox termChooser = new TermBox();

	private String[] head = null;
	private String[][] content = null;

	public MyScorePanel() {
		setLayout(null);
		setBackground(Color.white);

		scorep.setLocation(30, 25);
		scorep.getTopPanel().add(termChooser);
		scorep.getCenterPanel().setLayout(new BorderLayout());
		scorep.getCenterPanel()
				.add(new JScrollPane(table), BorderLayout.CENTER);
		add(scorep);
		setListener();
	}

	private void setListener() {
		termChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (termChooser.getSelectedIndex() != 0) {
					showTable();
				}
			}
		});
	}

	public void showTable() {
		head = null;
		content = null;
		table.clearSelection();
		dtm = new DefaultTableModel(0, 5);
		table.setModel(dtm);
		table.updateUI();

		Object o = logic.showScoreListHead();
		if (o instanceof String[]) {
			head = (String[]) o;
			o = logic.showScoreList(((TermVO) termChooser.getSelectedItem())
					.toString());
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof String[][]) {
				content = (String[][]) o;
				dtm.setDataVector(content, head);
			}
		}
		table.updateUI();
	}
}
