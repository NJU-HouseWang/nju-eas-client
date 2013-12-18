package NJU.HouseWang.nju_eas_client.ui.MainUI.StudentUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.StudentUILogic;
import NJU.HouseWang.nju_eas_client.vo.CourseDetailVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ChooseCommonPanel extends JPanel {
	private StudentUILogic logic = new StudentUILogic();
	private SubPanel choosep = new SubPanel("已选课程列表", 500, 135);
	private SubPanel coup = new SubPanel("通识课列表", 500, 280);
	private SubPanel infop = new SubPanel("课程详细信息", 230, 425);
	private JButton selectBtn = new JButton("选课");
	private JButton cancelBtn = new JButton("取消选课");

	private DefaultTableModel dtm1 = new DefaultTableModel(4, 5);
	private CommonTable infoTable1 = new CommonTable(dtm1);
	private String[] head1 = null;
	private String[][] content1 = null;

	private DefaultTableModel dtm2 = new DefaultTableModel(10, 5);
	private CommonTable infoTable2 = new CommonTable(dtm2);
	private String[] head2 = null;
	private String[][] content2 = null;

	public ChooseCommonPanel() {
		setLayout(null);
		setBackground(Color.white);

		choosep.setLocation(30, 25);
		coup.setLocation(30, 170);
		infop.setLocation(540, 25);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(infoTable2),
				BorderLayout.CENTER);
		choosep.getCenterPanel().setLayout(new BorderLayout());
		choosep.getCenterPanel().add(new JScrollPane(infoTable1),
				BorderLayout.CENTER);
		add(choosep);
		add(coup);
		add(infop);
		showCourseTable();
		showSelectTable();
		setListener();
	}

	private void setListener() {
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.selectCommonCourse(infoTable2.getValueAt(
						infoTable2.getSelectedRow(), 0).toString());
				if (fb == Feedback.OPERATION_SUCCEED) {
					showSelectTable();
					showCourseTable();
				} else {
					JOptionPane.showMessageDialog(null, "选课出现问题，请重试>_<");
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.cancelCommonCourse(infoTable2.getValueAt(
						infoTable2.getSelectedRow(), 0).toString());
				if (fb == Feedback.OPERATION_SUCCEED) {
					showSelectTable();
					showCourseTable();
				} else {
					JOptionPane.showMessageDialog(null, "取消选课出现问题，请重试>_<");
				}
			}
		});
		infoTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectRowNum = infoTable1.getSelectedRow();
				if (infoTable1.getSelectedRowCount() == 1 && selectRowNum != -1) {
					Object o = logic.showCourseDetail(logic.showCurrentTerm(),
							infoTable1.getValueAt(selectRowNum, 0).toString());
					if (o instanceof CourseDetailVO) {
						infop.setCenterPanel(new JPanel());
						JTextArea area = new JTextArea();
						area.setText("课程介绍：\r\n"
								+ ((CourseDetailVO) o).introduction
								+ "\r\n\r\n" + "推荐书目：\r\n"
								+ ((CourseDetailVO) o).book + "\r\n\r\n"
								+ "课程大纲：\r\n" + ((CourseDetailVO) o).syllabus
								+ "\r\n\r\n");
						area.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						area.setEditable(false);
						area.setBorder(null);
						infop.getCenterPanel().setLayout(new BorderLayout());
						infop.getCenterPanel().add(area, BorderLayout.CENTER);
						infop.getCenterPanel().add(cancelBtn,
								BorderLayout.SOUTH);
					}
				} else {
					infop.setCenterPanel(new JPanel());
				}
			}
		});
		infoTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectRowNum = infoTable2.getSelectedRow();
				if (infoTable2.getSelectedRowCount() == 1 && selectRowNum != -1) {
					Object o = logic.showCourseDetail(logic.showCurrentTerm(),
							infoTable2.getValueAt(selectRowNum, 0).toString());
					if (o instanceof CourseDetailVO) {
						infop.setCenterPanel(new JPanel());
						JTextArea area = new JTextArea();
						area.setText("课程介绍：\r\n"
								+ ((CourseDetailVO) o).introduction
								+ "\r\n\r\n" + "推荐书目：\r\n"
								+ ((CourseDetailVO) o).book + "\r\n\r\n"
								+ "课程大纲：\r\n" + ((CourseDetailVO) o).syllabus
								+ "\r\n\r\n");
						area.setFont(new Font("微软雅黑", Font.PLAIN, 12));
						area.setEditable(false);
						area.setBorder(null);
						infop.getCenterPanel().setLayout(new BorderLayout());
						infop.getCenterPanel().add(area, BorderLayout.CENTER);
						infop.getCenterPanel().add(selectBtn,
								BorderLayout.SOUTH);
					}
				} else {
					infop.setCenterPanel(new JPanel());
				}
			}
		});
	}

	private void showCourseTable() {
		head2 = null;
		content2 = null;
		infoTable2.clearSelection();
		dtm2 = new DefaultTableModel(0, 5);
		infoTable2.setModel(dtm2);
		infoTable2.updateUI();
		Object fb = logic.showSelectedCommonCourseListHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head2 = (String[]) fb;
			fb = logic.showSelectedCommonCourseList();
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content2 = (String[][]) fb;
				dtm2.setDataVector(content2, head2);
			}
		}
		infoTable2.updateUI();
	}

	private void showSelectTable() {
		head1 = null;
		content1 = null;
		infoTable1.clearSelection();
		dtm1 = new DefaultTableModel(0, 5);
		infoTable1.setModel(dtm1);
		infoTable1.updateUI();
		Object fb = logic.showCommonCourseListHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head1 = (String[]) fb;
			fb = logic.showYixuanCommonCourseList();
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content1 = (String[][]) fb;
				dtm1.setDataVector(content1, head1);
			}
		}
		infoTable1.updateUI();
	}

}
