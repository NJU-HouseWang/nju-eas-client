package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.GradeBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.ui.MainUI.ExportUI.ExportUI;
import NJU.HouseWang.nju_eas_client.uiLogic.DeptADUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class DeptStudentPanel extends JPanel {
	private DeptADUILogic logic = new DeptADUILogic();
	private FunctionBar fbar = new FunctionBar();
	private FunctionBtn[] fBtn = new FunctionBtn[1];
	private SubPanel stup = new SubPanel("本院学生列表", 940, 480);
	private GradeBox gradeChooser = new GradeBox();
	private RefreshBtn refreshBtn = new RefreshBtn();
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);

	private String[] head = null;
	private String[][] content = null;

	public DeptStudentPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("ExportBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		stup.setLocation(30, 70);
		gradeChooser.setPreferredSize(new Dimension(120, 20));
		gradeChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		stup.getTopPanel().add(gradeChooser);
		refreshBtn.setPreferredSize(new Dimension(24, 24));
		stup.getTopPanel().add(refreshBtn);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		stup.getCenterPanel().setLayout(new BorderLayout());
		stup.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		add(stup);
		setListener();
	}

	private void setListener() {
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExportUI(head, content);
			}
		});
	}

	private void showTable() {
		head = null;
		content = null;
		table.clearSelection();
		dtm = new DefaultTableModel(0, 5);
		table.setModel(dtm);
		table.updateUI();
		Object fb = logic.showStudentListHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showStudentList((String) gradeChooser.getSelectedItem());
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