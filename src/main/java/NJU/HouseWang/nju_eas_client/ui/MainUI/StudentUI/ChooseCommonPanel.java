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

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.CommonBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.StudentUILogic;
import NJU.HouseWang.nju_eas_client.vo.CourseDetailVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ChooseCommonPanel extends JPanel {
	private StudentUILogic logic = new StudentUILogic();
	private SubPanel choosep = new SubPanel("已选课程列表", 700, 145);
	private SubPanel coup = new SubPanel("通识课列表", 700, 380);
	private SubPanel infop = new SubPanel("课程详细信息", 230, 525);
	private CommonBtn selectBtn = new CommonBtn("选课");
	private CommonBtn cancelBtn = new CommonBtn("取消选课");

	private DefaultTableModel dtm1 = new DefaultTableModel(4, 5);
	private CommonTable infoTable1 = new CommonTable(dtm1);
	private String[] head1 = null;
	private String[][] content1 = null;

	private DefaultTableModel dtm2 = new DefaultTableModel(10, 5);
	private CommonTable infoTable2 = new CommonTable(dtm2);
	private String[] head2 = null;
	private String[][] content2 = null;

	private RefreshBtn rBtn1 = new RefreshBtn();
	private RefreshBtn rBtn2 = new RefreshBtn();

	private JTextArea area = new JTextArea();

	public ChooseCommonPanel() {
		setLayout(null);
		setBackground(Color.white);

		choosep.setLocation(30, 25);
		coup.setLocation(30, 175);
		infop.setLocation(740, 25);
		infop.getCenterPanel().setLayout(new BorderLayout());
		infop.getCenterPanel().add(area, BorderLayout.CENTER);
		area.setPreferredSize(new Dimension(220, 380));
		area.setSize(new Dimension(220, 390));
		area.setLineWrap(true);
		area.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		area.setEditable(false);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(infoTable2),
				BorderLayout.CENTER);
		choosep.getCenterPanel().setLayout(new BorderLayout());
		choosep.getCenterPanel().add(new JScrollPane(infoTable1),
				BorderLayout.CENTER);
		choosep.getTopPanel().add(rBtn1);
		coup.getTopPanel().add(rBtn2);
		add(choosep);
		add(coup);
		add(infop);
		showCourseTable();
		showSelectTable();
		setListener();
	}

	private void setListener() {
		rBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSelectTable();
			}
		});

		rBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCourseTable();
			}
		});

		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.selectCommonCourse(infoTable2.getValueAt(
						infoTable2.getSelectedRow(), 0).toString());
				if (fb == Feedback.OPERATION_SUCCEED) {
					showSelectTable();
					showCourseTable();
				} else {
					JOptionPane.showMessageDialog(null, fb.getContent());
				}
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.cancelCommonCourse(infoTable1.getValueAt(
						infoTable1.getSelectedRow(), 0).toString());
				if (fb == Feedback.OPERATION_SUCCEED) {
					showSelectTable();
					showCourseTable();
				} else {
					JOptionPane.showMessageDialog(null, fb.getContent());
				}
			}
		});
		infoTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				infoTable2.clearSelection();
				int selectRowNum = infoTable1.getSelectedRow();
				if (infoTable1.getSelectedRowCount() == 1 && selectRowNum != -1) {
					Object o = logic.showCourseDetail(logic.showCurrentTerm(),
							infoTable1.getValueAt(selectRowNum, 0).toString());
					if (o instanceof CourseDetailVO) {
						area.setText("");
						area.append("课程介绍：\r\n"
								+ ((CourseDetailVO) o).introduction
								+ "\r\n\r\n" + "推荐书目：\r\n"
								+ ((CourseDetailVO) o).book + "\r\n\r\n"
								+ "课程大纲：\r\n" + ((CourseDetailVO) o).syllabus
								+ "\r\n\r\n");
						selectBtn.setVisible(false);
						cancelBtn.setVisible(true);
						infop.getCenterPanel().add(cancelBtn,
								BorderLayout.SOUTH);
					}
				} else {
					area.setText("");
					cancelBtn.setVisible(false);
					selectBtn.setVisible(false);
				}
			}
		});
		infoTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				infoTable1.clearSelection();
				int selectRowNum = infoTable2.getSelectedRow();
				System.out.println(infoTable2.getSelectedRowCount());
				System.out.println(selectRowNum);
				if (infoTable2.getSelectedRowCount() == 1 && selectRowNum != -1) {
					Object o = logic.showCourseDetail(logic.showCurrentTerm(),
							infoTable2.getValueAt(selectRowNum, 0).toString());
					if (o instanceof CourseDetailVO) {
						area.setText("");
						area.append("课程介绍：\r\n"
								+ ((CourseDetailVO) o).introduction
								+ "\r\n\r\n" + "推荐书目：\r\n"
								+ ((CourseDetailVO) o).book + "\r\n\r\n"
								+ "课程大纲：\r\n" + ((CourseDetailVO) o).syllabus
								+ "\r\n\r\n");
						selectBtn.setVisible(true);
						cancelBtn.setVisible(false);
						infop.getCenterPanel().add(selectBtn,
								BorderLayout.SOUTH);
					}
				} else {
					area.setText("");
					cancelBtn.setVisible(false);
					selectBtn.setVisible(false);
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
