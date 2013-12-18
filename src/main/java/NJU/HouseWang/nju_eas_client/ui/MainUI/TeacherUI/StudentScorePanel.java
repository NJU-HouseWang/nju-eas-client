package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.TeacherUILogic;
import NJU.HouseWang.nju_eas_client.vo.CourseVO;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class StudentScorePanel extends JPanel {
	private TeacherUILogic logic = new TeacherUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[3];
	private SubPanel scorep = new SubPanel("学生成绩列表", 740, 380);
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);
	private RefreshBtn reBtn = new RefreshBtn();
	private JComboBox<CourseVO> couChooser = new JComboBox<CourseVO>();

	private String[] head = null;
	private String[][] content = null;

	public StudentScorePanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("ImportBtn");
		fBtn[2] = new FunctionBtn("ExportBtn");

		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		scorep.setLocation(30, 70);

		scorep.getCenterPanel().setLayout(new BorderLayout());
		scorep.getCenterPanel()
				.add(new JScrollPane(table), BorderLayout.CENTER);
		add(scorep);
		initCouChooser();
		setListener();
		fBtn[0].setEnabled(false);
	}

	private void initCouChooser() {
		couChooser.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		couChooser.setPreferredSize(new Dimension(120, 20));
		couChooser.addItem(new CourseVO("null", "请选择课程..."));
		Object o = logic.showMyCourseList();
		if (o instanceof ArrayList<?>) {
			for (Object obj : ((ArrayList<?>) o)) {
				if (obj instanceof String) {
					CourseVO cou = new CourseVO();
					cou.couId = ((String) obj).split("；")[0];
					cou.couName = ((String) obj).split("；")[1];
					couChooser.addItem(cou);
				}
			}
		}
	}

	private void setListener() {
		reBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

		couChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable();
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fBtn[0].getName().equals("AddBtn")) {
					JOptionPane.showMessageDialog(null, "请在表格中输入学生成绩，按完成按钮提交。");
					dtm = new DefaultTableModel(0, 5) {
						public boolean isCellEditable(int arg0, int arg1) {
							return true;
						}
					};
					table.setModel(dtm);
					dtm.setDataVector(content, head);
					table.updateUI();
				} else {
					Feedback fb = logic.recordScore(logic.showCurrentTerm(),
							((CourseVO) couChooser.getSelectedItem()).couId,
							content);
					JOptionPane.showMessageDialog(null, fb.getContent());
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
		fBtn[0].setEnabled(false);

		String couId = ((CourseVO) couChooser.getSelectedItem()).couId;
		if (!couId.equals("null")) {
			Object o = logic.showStudentScoreListHead();
			if (o instanceof String[]) {
				head = (String[]) o;
				o = logic.showStudentScoreList(logic.showCurrentTerm(), couId);
				if (o instanceof Feedback) {
					JOptionPane.showMessageDialog(null,
							((Feedback) o).getContent());
				} else if (o instanceof String[][]) {
					content = (String[][]) o;
					dtm.setDataVector(content, head);
					fBtn[0].setEnabled(true);
				}
			}
		}
		table.updateUI();
	}
}
