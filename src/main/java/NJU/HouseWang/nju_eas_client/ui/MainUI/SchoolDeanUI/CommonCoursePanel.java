package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class CommonCoursePanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private FunctionBar fbar = new FunctionBar();
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private SubPanel cp = new SubPanel("通识课列表", 740, 380);
	private DefaultTableModel dtm = new DefaultTableModel(0, 5);
	private CommonTable table = new CommonTable(dtm);
	private JButton refreshBtn = new JButton();

	private String[] head;
	private String[][] content;

	public CommonCoursePanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("ModifyBtn");
		fBtn[2] = new FunctionBtn("DelBtn");
		fBtn[3] = new FunctionBtn("ImportBtn");
		fBtn[4] = new FunctionBtn("ExportBtn");

		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		cp.setLocation(30, 70);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		cp.getCenterPanel().setLayout(new BorderLayout());
		cp.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		refreshBtn.setPreferredSize(new Dimension(25, 25));
		cp.getTopPanel().add(refreshBtn);
		add(cp);
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
			public void actionPerformed(ActionEvent arg0) {
				new AddCommonCourseUI("通识课", head);
			}
		});

		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					String[] origin = new String[table.getColumnCount()];
					for (int i = 0; i < origin.length; i++) {
						origin[i] = (String) table.getValueAt(selectRowNum, i);
					}
					new EditCommonCourseUI("通识课", head, origin);
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR.getContent());
				}
			}
		});

		fBtn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					System.out.println(table.getValueAt(selectRowNum, 0));
					Feedback fb = logic.delCommonCourse((String) table
							.getValueAt(selectRowNum, 0));
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR.getContent());
				}
			}
		});

	}

	/* *
	 * 显示表格
	 */
	private void showTable() {
		head = null;
		content = null;
		Object fb = logic.showInfoListHead("common_course_list");
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showInfoList("common_course_list", "");
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