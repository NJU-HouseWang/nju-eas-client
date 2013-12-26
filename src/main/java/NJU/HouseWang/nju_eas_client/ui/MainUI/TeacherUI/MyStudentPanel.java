package NJU.HouseWang.nju_eas_client.ui.MainUI.TeacherUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MyStudentPanel extends JPanel {
	private TeacherUILogic logic = new TeacherUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[1];
	private SubPanel stup = null;
	private DefaultTableModel dtm = new DefaultTableModel(40, 5);
	private CommonTable table = new CommonTable(dtm);
	private JComboBox<CourseVO> couChooser = new JComboBox<CourseVO>();
	private RefreshBtn reBtn = new RefreshBtn();

	private String[] head = null;
	private String[][] content = null;

	public MyStudentPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("ExportBtn");

		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		stup = new SubPanel("我的学生列表", 940, 480);
		stup.setLocation(30, 70);

		stup.getTopPanel().add(couChooser);
		stup.getTopPanel().add(reBtn);

		stup.getCenterPanel().setLayout(new BorderLayout());
		stup.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		add(stup);
		initCouChooser();
		setListener();
	}

	private void initCouChooser() {
		couChooser.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		couChooser.setPreferredSize(new Dimension(120, 20));
		couChooser.addItem(new CourseVO("null", "请选择课程..."));
		Object o = logic.showMyCourseList();
		if (o instanceof String[][]) {
			String[][] content = (String[][]) o;
			for (int i = 0; i < content.length; i++) {
				CourseVO cou = new CourseVO();
				cou.couId = content[i][0];
				cou.couName = content[i][1];
				couChooser.addItem(cou);
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
	}

	public void showTable() {
		head = null;
		content = null;
		table.clearSelection();
		dtm = new DefaultTableModel(0, 5);
		table.setModel(dtm);
		table.updateUI();

		String couId = ((CourseVO) couChooser.getSelectedItem()).couId;
		if (!couId.equals("null")) {
			Object o = logic.showStudentListHead();
			if (o instanceof String[]) {
				head = (String[]) o;
				o = logic.showMyStudentList(logic.showCurrentTerm(), couId);
				if (o instanceof Feedback) {
					JOptionPane.showMessageDialog(null,
							((Feedback) o).getContent());
				} else if (o instanceof String[][]) {
					content = (String[][]) o;
					dtm.setDataVector(content, head);
				}
			}
		}
		table.updateUI();
	}
}
