package NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.StudentUILogic;
import NJU.HouseWang.nju_eas_client.vo.CourseDetailVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ByelectPanel extends JPanel {
	private StudentUILogic logic = new StudentUILogic();
	private SubPanel coup = new SubPanel("通识课列表（双击即可选中）", 700, 525);
	private SubPanel infop = new SubPanel("课程详细信息", 230, 525);

	private DefaultTableModel dtm = new DefaultTableModel(10, 5);
	private CommonTable infoTable = new CommonTable(dtm);
	private String[] head = null;
	private String[][] content = null;

	private RefreshBtn rBtn = new RefreshBtn();

	private JTextArea area = new JTextArea();

	public ByelectPanel() {
		setLayout(null);
		setBackground(Color.white);

		coup.setLocation(30, 25);
		infop.setLocation(740, 25);
		infop.getCenterPanel().setLayout(new BorderLayout());
		infop.getCenterPanel().add(area, BorderLayout.CENTER);
		area.setPreferredSize(new Dimension(220, 380));
		area.setSize(new Dimension(220, 390));
		area.setLineWrap(true);
		area.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		area.setEditable(false);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(infoTable),
				BorderLayout.CENTER);
		coup.getTopPanel().add(rBtn);
		add(coup);
		add(infop);
		showCourseTable();
		setListener();
	}

	private void setListener() {
		rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCourseTable();
			}
		});

		infoTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectRowNum = infoTable.getSelectedRow();
				int clickedCount = e.getClickCount();
				if (clickedCount == 2) {
					if (infoTable.getSelectedRowCount() == 1
							&& selectRowNum != -1) {
						Feedback fb = logic.byelectCommonCourse(infoTable
								.getValueAt(selectRowNum, 0).toString());
						if (fb == Feedback.OPERATION_SUCCEED) {
							JOptionPane.showMessageDialog(null, "补选成功！");
						} else {
							JOptionPane.showMessageDialog(null,
									"补选失败：" + fb.getContent());
						}
					}
				} else if (clickedCount == 1) {
					if (infoTable.getSelectedRowCount() == 1
							&& selectRowNum != -1) {
						Object o = logic.showCourseDetail(logic
								.showCurrentTerm(),
								infoTable.getValueAt(selectRowNum, 0)
										.toString());
						if (o instanceof CourseDetailVO) {
							area.setText("");
							area.append("课程介绍：\r\n"
									+ ((CourseDetailVO) o).introduction
									+ "\r\n\r\n" + "推荐书目：\r\n"
									+ ((CourseDetailVO) o).book + "\r\n\r\n"
									+ "课程大纲：\r\n"
									+ ((CourseDetailVO) o).syllabus
									+ "\r\n\r\n");
						}
					} else {
						area.setText("");
					}
				}
			}
		});
	}

	private void showCourseTable() {
		head = null;
		content = null;
		infoTable.clearSelection();
		dtm = new DefaultTableModel(0, 5);
		infoTable.setModel(dtm);
		infoTable.updateUI();
		Object fb = logic.showSelectedCommonCourseListHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showSelectedCommonCourseList();
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
				dtm.setDataVector(content, head);
			}
		}
		infoTable.updateUI();
	}
}
