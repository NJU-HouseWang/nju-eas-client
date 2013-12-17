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
import NJU.HouseWang.nju_eas_client.uiLogic.DeptADUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class DeptCoursePanel extends JPanel {
	private DeptADUILogic logic = new DeptADUILogic();
	private FunctionBar fbar = new FunctionBar();
	private FunctionBtn[] fBtn = new FunctionBtn[2];
	private SubPanel coup = new SubPanel("本院课程列表", 740, 380);
	private GradeBox gradeChooser = new GradeBox();
	private RefreshBtn refreshBtn = new RefreshBtn();
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);

	private String[] head = null;
	private String[][] content = null;
	
	public DeptCoursePanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("ModifyBtn");
		fBtn[1] = new FunctionBtn("ExportBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		coup.setLocation(30, 70);
		gradeChooser.setPreferredSize(new Dimension(120, 20));
		gradeChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		coup.getTopPanel().add(gradeChooser);
		refreshBtn.setPreferredSize(new Dimension(24, 24));
		coup.getTopPanel().add(refreshBtn);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		add(coup);
		setListener();
		showTable();
	}

	private void setListener() {
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					String[] origin = new String[table.getColumnCount()];
					for (int i = 0; i < origin.length; i++) {
						origin[i] = (String) table.getValueAt(selectRowNum, i);
					}
					new EditCourseUI("课程", head, origin);
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR.getContent());
				}
			}
		});

		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		Object fb = logic.showCourseListHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showCourseListContent((String) gradeChooser
					.getSelectedItem());
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