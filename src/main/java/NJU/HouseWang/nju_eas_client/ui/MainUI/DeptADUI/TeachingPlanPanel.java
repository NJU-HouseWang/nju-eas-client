package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.ClickedLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CTable;
import NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.EduFrameworkMap;
import NJU.HouseWang.nju_eas_client.uiLogic.DeptADUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TPDeptVO;

@SuppressWarnings("serial")
public class TeachingPlanPanel extends JPanel {
	private DeptADUILogic logic = new DeptADUILogic();
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[2];
	private SubPanel tpp = null;
	private SubPanel accessoryp = null;
	private SubPanel localStatuesp = null;
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CTable table = null;
	private RefreshBtn refreshBtn = new RefreshBtn();

	private String[][] content = null;
	private String[] head = null;

	public TeachingPlanPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		tpp = new SubPanel("教学计划  ", 500, 380);
		tpp.setLocation(30, 70);
		tpp.getTopPanel().add(refreshBtn);

		accessoryp = new SubPanel("附件", 230, 150);
		accessoryp.setLocation(540, 70);

		localStatuesp = new SubPanel("当前状态", 230, 150);
		localStatuesp.setLocation(540, 230);

		initEmptyTPTable();
		tpp.getCenterPanel().setLayout(new BorderLayout());
		tpp.getCenterPanel().add(new JScrollPane(table), BorderLayout.CENTER);

		add(tpp);
		add(accessoryp);
		add(localStatuesp);
		setListener();
		showTP();
	}

	private void setListener() {
		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddTpUI();
			}
		});
		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feedback fb = logic.delTP();
				JOptionPane.showMessageDialog(null, fb.getContent());
			}
		});
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTP();
			}
		});
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

	private void showTPTable() {
		head = null;
		content = null;
		Object fb = logic.showTPHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			dtm.setColumnIdentifiers(head);
			fb = logic.showTPContent();
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

	private void showTP() {
		Object o = logic.showTPStatus();
		if (o instanceof Feedback) {
//			JOptionPane.showMessageDialog(null, ((Feedback) o).getContent());
		} else if (o instanceof TPDeptVO) {
			if (!((TPDeptVO) o).fileName.equals("null")) {
				ClickedLabel lb = new ClickedLabel(((TPDeptVO) o).fileName);
				accessoryp.getCenterPanel().add(lb);
				lb.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						Feedback fb = logic.downloadTPFile();
						if (fb == Feedback.OPERATION_SUCCEED) {
							JOptionPane.showMessageDialog(null, "文件已打开，请另存。");
						} else {
							JOptionPane.showMessageDialog(null, fb.getContent());
						}
					}
				});
			}
			String status = null;
			switch (((TPDeptVO) o).tpState) {
			case 0:
				status = "未提交";
				refreshBtn.setVisible(false);
				dtm.removeRow(0);
				break;
			case 1:
				status = "审核通过";
				showTPTable();
				refreshBtn.setVisible(true);
				break;
			case 2:
				status = "审核不通过，请重新提交";
				showTPTable();
				refreshBtn.setVisible(true);
				break;
			default:
				status = "错误代码" + ((TPDeptVO) o).tpState + "";
			}
			JLabel lb2 = new JLabel(status);
			lb2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			localStatuesp.getCenterPanel().add(lb2);
		}
	}
}