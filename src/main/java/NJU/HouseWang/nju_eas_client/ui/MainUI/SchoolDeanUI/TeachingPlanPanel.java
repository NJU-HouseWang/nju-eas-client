package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.ClickedLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.DeptVO;

@SuppressWarnings("serial")
public class TeachingPlanPanel extends JPanel {
	private SchoolDeanUILogic logic = new SchoolDeanUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[2];
	private SubPanel tpp = null;
	private SubPanel accessoryp = null;
	private SubPanel localStatuesp = null;
	private JComboBox<DeptVO> deptChooser = new JComboBox<DeptVO>();
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CTable table = null;

	private String[][] content = new String[][] { { " ", " ", " ", " ", " ",
			" ", " " } };
	private String[] head = new String[] { " ", " ", " ", " ", " ", " ", " " };

	public TeachingPlanPanel() {
		// 构造界面
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("TrueBtn");
		fBtn[1] = new FunctionBtn("FalseBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);
		deptChooser.setPreferredSize(new Dimension(120, 20));
		// 教学计划子窗口
		tpp = new SubPanel("教学计划  ", 500, 380);
		tpp.setLocation(30, 70);
		tpp.getTopPanel().add(deptChooser);
		// 附件子窗口
		accessoryp = new SubPanel("附件", 230, 150);
		accessoryp.setLocation(540, 70);
		// 状态子窗口
		localStatuesp = new SubPanel("当前状态", 230, 150);
		localStatuesp.setLocation(540, 230);
		// 教学计划列表（开始时为空）
		initEmptyTPTable();
		tpp.getCenterPanel().setLayout(new BorderLayout());
		tpp.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);
		// 添加子窗口
		add(tpp);
		add(accessoryp);
		add(localStatuesp);
		// 设置监听
		setListener();
		// 初始化院系列表
		initDeptList();
		// 设置按钮初始状态
		fBtn[0].setEnabled(false);
		fBtn[1].setEnabled(false);
	}

	private void setListener() {
		deptChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<DeptVO> c = (JComboBox<DeptVO>) e.getSource();
				final DeptVO dept = (DeptVO) c.getSelectedItem();
				if (dept.deptId.equals("null")) {
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(false);
				} else {
					fBtn[0].setEnabled(true);
					fBtn[1].setEnabled(true);
					showTPTable(dept.deptId);

					ClickedLabel lb = new ClickedLabel(dept.fileName);
					accessoryp.getCenterPanel().add(lb);
					lb.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							logic.downloadTPFile(dept.deptId);
						}
					});

					JLabel stateslb = new JLabel();
					stateslb.setFont(new Font("微软雅黑", Font.PLAIN, 12));
					localStatuesp.getCenterPanel().add(stateslb);
					if (dept.isCommitted) {
						switch (dept.tpState) {
						case 0:
							stateslb.setText("未审核");
							fBtn[0].setEnabled(true);
							fBtn[1].setEnabled(true);
						case 1:
							stateslb.setText("审核通过");
							fBtn[0].setEnabled(false);
							fBtn[1].setEnabled(false);
						case 2:
							stateslb.setText("审核不过");
							fBtn[0].setEnabled(false);
							fBtn[1].setEnabled(false);
						}
					} else {
						stateslb.setText("未提交");
						fBtn[0].setEnabled(false);
						fBtn[1].setEnabled(false);
					}
				}
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.auditTP(
						((DeptVO) deptChooser.getSelectedItem()).deptId, 1);
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});

		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Feedback fb = logic.auditTP(
						((DeptVO) deptChooser.getSelectedItem()).deptId, 2);
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});
	}

	private void initDeptList() {
		Object o = logic.showTPList();
		if (o instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) o).getContent());
		} else if (o instanceof ArrayList<?>) {
			DeptVO nul = new DeptVO();
			nul.deptId = "null";
			nul.deptName = "请选择院系...";
			deptChooser.addItem(nul);
			for (Object d : (ArrayList<?>) o) {
				if (d instanceof DeptVO) {
					deptChooser.addItem((DeptVO) d);
				}
			}
		}
	}

	private void initEmptyTPTable() {
		content = new String[][] { { " ", " ", " ", " ", " ", " ", " " } };
		head = new String[] { " ", " ", " ", " ", " ", " ", " " };
		map = new EduFrameworkMap(content);
		dtm = new DefaultTableModel(content.length, content[0].length) {
			public boolean isCellEditable(int indexRow, int indexColumn) {
				return false;
			}
		};
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				dtm.setValueAt(content[i][j], i, j);
			}
		}
		dtm.setColumnIdentifiers(head);
		table = new CTable(map, dtm);
		table.setEnabled(false);
	}

	private void showTPTable(String dept) {
		head = null;
		content = null;
		Object fb = logic.showTPHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			dtm.setColumnIdentifiers(head);
			fb = logic.showTPContent(dept);
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
				for (int i = 0; i < content.length; i++) {
					for (int j = 0; j < content[i].length; j++) {
						dtm.setValueAt(content[i][j], i, j);
					}
				}
			}
		}
		table.updateUI();
	}
}
