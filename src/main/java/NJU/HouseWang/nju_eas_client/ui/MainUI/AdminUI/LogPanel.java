package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Table.CommonTable;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;

public class LogPanel extends JPanel {
	private static int FUNC_NUM = 1;// 功能按钮的数量
	private static String[] FUNC_BTN_NAME = { "ExportBtn" };// 功能按钮的名称
	private AdminUILogic logic = new AdminUILogic();// 管理员界面的逻辑
	private JPanel fbar = new FunctionBar();// 功能按钮栏
	private FunctionBtn[] fBtn = new FunctionBtn[FUNC_NUM];// 功能按钮
	private SubPanel sp = new SubPanel("操作日志列表", 740, 380);// 子界面
	private JButton refreshBtn = new JButton();// 刷新按钮
	private JTextField conditiontf = new JTextField();// 条件输入框
	private JButton searchBtn = new JButton();// 搜索按钮

	private JScrollPane scrollp = new JScrollPane();// 表格滚动框
	private DefaultTableModel dtm = new DefaultTableModel(20, 5);// 表格Model
	@SuppressWarnings("serial")
	private CommonTable table = new CommonTable(dtm) {// 表格
		// 设置表格不可编辑
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String[] head = null;// 表头
	private String[][] content = null;// 表格内容

	public LogPanel() {
		setLayout(null);
		setBackground(Color.white);
		for (int i = 0; i < FUNC_NUM; i++) {
			fBtn[i] = new FunctionBtn(FUNC_BTN_NAME[i]);
			fbar.add(fBtn[i]);
		}

		sp.setLocation(30, 70);
		refreshBtn.setBounds(3, 3, 24, 24);
		refreshBtn.setPreferredSize(new Dimension(22, 22));
		conditiontf.setBounds(562, 4, 150, 22);
		conditiontf.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		conditiontf.setBorder(null);
		searchBtn.setBounds(712, 4, 22, 22);
		searchBtn.setBorder(null);
		// sp.getTopPanel().setLayout(null);
		sp.getTopPanel().add(refreshBtn);
		// sp.getTopPanel().add(conditiontf);
		// sp.getTopPanel().add(searchBtn);

		scrollp.getViewport().setBackground(Color.white);
		scrollp.setViewportView(table);
		sp.getCenterPanel().setLayout(new BorderLayout());
		sp.getCenterPanel().add(scrollp, BorderLayout.CENTER);

		setListener();
		add(fbar);
		add(sp);
	}

	/* *
	 * 显示表格
	 */
	private void showTable() {
		head = null;
		content = null;
		String listName = "log_list";
		Object fb = logic.showTableHead(listName);
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showTableContent(listName);
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

	/* *
	 * 加监听
	 */
	private void setListener() {
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "导出列表");
			}
		});
	}

}
