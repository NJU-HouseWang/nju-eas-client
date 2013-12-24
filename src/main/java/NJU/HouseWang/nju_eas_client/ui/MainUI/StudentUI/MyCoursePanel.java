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

public class MyCoursePanel extends JPanel {
	private StudentUILogic logic = new StudentUILogic();
	private SubPanel coup = new SubPanel("我的课程列表", 700, 520);
	private SubPanel infop = new SubPanel("详细信息", 230, 525);
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);
	private RefreshBtn reBtn = new RefreshBtn();

	private String[] head = null;
	private String[][] content = null;

	private JTextArea area = new JTextArea();

	public MyCoursePanel() {
		setLayout(null);
		setBackground(Color.white);

		coup.setLocation(30, 25);
		coup.getTopPanel().add(reBtn);

		area.setPreferredSize(new Dimension(220, 380));
		area.setSize(new Dimension(220, 390));
		area.setLineWrap(true);
		area.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		area.setEditable(false);

		infop.setLocation(740, 25);
		infop.getCenterPanel().setLayout(new BorderLayout());
		infop.getCenterPanel().add(area, BorderLayout.CENTER);

		coup.getCenterPanel().setLayout(new BorderLayout());
		coup.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		add(coup);
		add(infop);
		setListener();

	}

	private void setListener() {
		reBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectRowNum = table.getSelectedRow();
				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					Object o = logic.showCourseDetail(logic.showCurrentTerm(),
							table.getValueAt(selectRowNum, 0).toString());
					if (o instanceof CourseDetailVO) {
						area.append("课程介绍：\r\n"
								+ ((CourseDetailVO) o).introduction
								+ "\r\n\r\n" + "推荐书目：\r\n"
								+ ((CourseDetailVO) o).book + "\r\n\r\n"
								+ "课程大纲：\r\n" + ((CourseDetailVO) o).syllabus
								+ "\r\n\r\n");
					}
				}
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
			fb = logic.showMyCourseList();
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
