package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.DeptBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.GradeBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.ComboBox.TermBox;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.DeptVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

@SuppressWarnings("serial")
public class InfoListPanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private SubPanel infop = new SubPanel("列表：", 740, 420);
	private JComboBox<String> listChooser = new JComboBox<String>();
	private DeptBox deptChooser = new DeptBox();
	private TermBox termChooser = new TermBox();
	private GradeBox gradeChooser = new GradeBox();
	private JLabel deptlb = new JLabel("院系：");
	private JLabel termlb = new JLabel("学年：");
	private JLabel gradelb = new JLabel("年级：");
	private RefreshBtn showBtn = new RefreshBtn();

	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);

	private String[] head = null;
	private String[][] content = null;

	public InfoListPanel() {
		setLayout(null);
		setBackground(Color.white);

		infop.setLocation(30, 30);

		listChooser.setPreferredSize(new Dimension(120, 20));
		listChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		deptChooser.setPreferredSize(new Dimension(120, 20));
		deptChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		termChooser.setPreferredSize(new Dimension(120, 20));
		termChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		gradeChooser.setPreferredSize(new Dimension(120, 20));
		gradeChooser.setFont(new Font("微软雅黑", Font.BOLD, 14));
		showBtn.setPreferredSize(new Dimension(20, 20));
		showBtn.setFont(new Font("微软雅黑", Font.BOLD, 14));

		deptlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		deptlb.setForeground(Color.white);
		termlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		termlb.setForeground(Color.white);
		gradelb.setFont(new Font("微软雅黑", Font.BOLD, 14));
		gradelb.setForeground(Color.white);

		listChooser.addItem("学生列表");
		listChooser.addItem("老师列表");
		listChooser.addItem("课程列表");

		infop.getTopPanel().add(listChooser);
		infop.getTopPanel().add(deptlb);
		infop.getTopPanel().add(deptChooser);
		infop.getTopPanel().add(termlb);
		infop.getTopPanel().add(termChooser);
		infop.getTopPanel().add(gradelb);
		infop.getTopPanel().add(gradeChooser);
		infop.getTopPanel().add(showBtn);

		deptlb.setVisible(true);
		termlb.setVisible(false);
		gradelb.setVisible(true);
		deptChooser.setVisible(true);
		termChooser.setVisible(false);
		gradeChooser.setVisible(true);

		infop.getCenterPanel().setLayout(new BorderLayout());
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		infop.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		add(infop);
		setListener();
	}

	private void setListener() {
		listChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				if (cb.getSelectedItem().equals("课程列表")) {
					deptlb.setVisible(true);
					termlb.setVisible(true);
					gradelb.setVisible(true);
					deptChooser.setVisible(true);
					termChooser.setVisible(true);
					gradeChooser.setVisible(true);
				} else if (cb.getSelectedItem().equals("老师列表")) {
					deptlb.setVisible(false);
					termlb.setVisible(false);
					gradelb.setVisible(false);
					deptChooser.setVisible(false);
					termChooser.setVisible(false);
					gradeChooser.setVisible(false);
				} else {
					deptlb.setVisible(true);
					termlb.setVisible(false);
					gradelb.setVisible(true);
					deptChooser.setVisible(true);
					termChooser.setVisible(false);
					gradeChooser.setVisible(true);
				}
			}
		});

		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
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

		String listName = (String) listChooser.getSelectedItem();
		String dept = ((DeptVO) deptChooser.getSelectedItem()).deptName;
		String year = (String) termChooser.getSelectedItem();
		String grade = (String) gradeChooser.getSelectedItem();
		if (listName.equals("学生列表")) {
			listName = "student_list";
			Object o = logic.showInfoList(listName, grade + "，" + dept);
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof String[][]) {
				content = (String[][]) o;
			}
		} else if (listName.equals("老师列表")) {
			listName = "teacher_list";
			Object o = logic.showInfoList(listName, "all");
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof String[][]) {
				content = (String[][]) o;
			}
		} else {
			listName = "course_list";
			Object o = logic.showInfoList(listName, year + "；" + grade + "，"
					+ dept);
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof String[][]) {
				content = (String[][]) o;
			}
		}
		Object o = logic.showInfoListHead(listName);
		if (o instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) o).getContent());
		} else if (o instanceof String[]) {
			head = (String[]) o;
		}

		dtm.setDataVector(content, head);
		table.updateUI();
	}
}