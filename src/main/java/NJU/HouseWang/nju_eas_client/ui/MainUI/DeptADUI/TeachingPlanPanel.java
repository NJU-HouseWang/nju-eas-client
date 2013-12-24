package NJU.HouseWang.nju_eas_client.ui.MainUI.DeptADUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Bar.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Button.RefreshBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.ClickedLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.CommonLabel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Panel.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
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
	private EduFrameworkMap map = null;
	private DefaultTableModel dtm = null;
	private CardLayout cl = new CardLayout();
	private JPanel cardp = new JPanel(cl);
	private CommonTable table1 = null;
	private JScrollPane js1 = new JScrollPane();
	private CTable table2 = null;
	private JScrollPane js2 = new JScrollPane();

	private SubPanel accessoryp = null;
	private SubPanel localStatuesp = null;
	private RefreshBtn refreshBtn = new RefreshBtn();

	private String[][] content = null;
	private String[] head = null;

	public TeachingPlanPanel() {
		setLayout(null);
		setBackground(Color.white);
		fbar = new FunctionBar();
		fbar.setLocation(0, 0);
		fBtn[0] = new FunctionBtn("ImportBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		for (int i = 0; i < fBtn.length; i++) {
			fbar.add(fBtn[i]);
		}
		add(fbar);

		tpp = new SubPanel("教学计划  ", 700, 480);
		tpp.setLocation(30, 70);
		tpp.getTopPanel().add(refreshBtn);

		accessoryp = new SubPanel("附件", 230, 70);
		accessoryp.setLocation(740, 70);

		localStatuesp = new SubPanel("当前状态", 230, 70);
		localStatuesp.setLocation(740, 150);

		tpp.getCenterPanel().setLayout(new BorderLayout());
		tpp.getCenterPanel().add(cardp, BorderLayout.CENTER);
		cardp.add(js1, "1");
		cardp.add(js2, "2");

		add(tpp);
		add(accessoryp);
		add(localStatuesp);
		showTP();
		setListener();
	}

	private void setListener() {
		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ImportTeachingPlanUI();
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

	private void showTP() {
		Object o = logic.showTPStatus();
		if (o instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) o).getContent());
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
			} else {
				CommonLabel lb = new CommonLabel("教学计划未提交");
				lb.setForeground(Color.BLACK);
				accessoryp.getCenterPanel().add(lb);
			}
			String status = null;
			switch (((TPDeptVO) o).tpState) {
			case 0:
				status = "教学计划未提交";
				refreshBtn.setVisible(false);
				break;
			case 1:
				status = "审核通过";
				showTeachingPlan();
				refreshBtn.setVisible(true);
				break;
			case 2:
				status = "审核不通过，请重新提交";
				showTeachingPlan();
				refreshBtn.setVisible(true);
				break;
			default:
				status = "错误代码" + ((TPDeptVO) o).tpState + "";
			}
			CommonLabel lb2 = new CommonLabel(status);
			lb2.setForeground(Color.BLACK);
			localStatuesp.getCenterPanel().add(lb2);
		}
	}

	public void showTeachingPlan() {
		content = null;
		head = null;

		Object fb = logic.showTPHead();
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showTPContent();
			if ((fb instanceof Feedback) && fb != Feedback.LIST_EMPTY) {
				JOptionPane.showMessageDialog(null,
						((Feedback) fb).getContent());
			} else if (fb instanceof String[][]) {
				content = (String[][]) fb;
			}
		}
		if (content == null) {
			fBtn[0].setEnabled(true);
			fBtn[1].setEnabled(true);
			fBtn[2].setEnabled(false);
			DefaultTableModel dtm1 = new DefaultTableModel(1, 1);
			dtm1.setValueAt("教学计划未提交。", 0, 0);
			table1 = new CommonTable(dtm1);
			table1.setEnabled(false);
			js1.setViewportView(table1);
			cl.show(cardp, "1");
		} else {
			fBtn[0].setEnabled(false);
			fBtn[1].setEnabled(false);
			fBtn[2].setEnabled(true);
			map = new EduFrameworkMap(content);
			dtm = new DefaultTableModel(content.length, head.length);
			table2 = new CTable(map, dtm);
			dtm.setColumnIdentifiers(head);
			for (int i = 0; i < content.length; i++) {
				for (int j = 0; j < content[i].length; j++) {
					if (content[i][j].contains("null-null")) {
						content[i][j] = content[i][j].replaceAll("null-null",
								"");
					}
					dtm.setValueAt(content[i][j], i, j);
				}
			}
			table2.setEnabled(false);
			js2.setViewportView(table2);
			cl.show(cardp, "2");
		}
		this.repaint();
		this.validate();
	}
}