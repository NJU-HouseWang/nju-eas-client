package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import NJU.HouseWang.nju_eas_client.ui.MainUI.AddItemUI.AddUserUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.EditItemUI.EditUserUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.ExportUI.ExportUI;
import NJU.HouseWang.nju_eas_client.ui.MainUI.ImportUI.ImportUI;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

@SuppressWarnings("serial")
public class UserPanel extends JPanel {
	private static int FUNC_NUM = 5;// 功能按钮的数量
	private static String[] FUNC_BTN_NAME = { "AddBtn", "ModifyBtn", "DelBtn",
			"ImportBtn", "ExportBtn" };// 功能按钮的名称
	private UserTypeVO[] ut = new UserTypeVO[6];// 用户类型列表
	private AdminUILogic logic = new AdminUILogic();// 管理员界面的逻辑
	private FunctionBar fbar = new FunctionBar();// 功能按钮栏
	private FunctionBtn[] fBtn = new FunctionBtn[FUNC_NUM];// 功能按钮
	private SubPanel sp = new SubPanel("", 940, 480);// 子界面
	private JComboBox<UserTypeVO> userTypecb = new JComboBox<UserTypeVO>();// 用户类型下拉框
	private RefreshBtn refreshBtn = new RefreshBtn();// 刷新按钮

	private JScrollPane scrollp = new JScrollPane();// 表格滚动框
	private DefaultTableModel dtm = new DefaultTableModel();// 表格Model
	private CommonTable table = new CommonTable(dtm) {// 表格
		// 设置表格不可编辑
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String[] head = null;// 表头
	private String[][] content = null;// 表格内容

	/* *
	 * 管理员界面下的管理系统用户的Panel构造方法
	 */
	public UserPanel() {
		ut[0] = new UserTypeVO("null", "请选择用户类型...");
		ut[1] = new UserTypeVO("Login", "全体用户");
		ut[2] = new UserTypeVO("SchoolDean", "学校教务老师");
		ut[3] = new UserTypeVO("DeptAD", "院系教务老师");
		ut[4] = new UserTypeVO("Teacher", "任课老师");
		ut[5] = new UserTypeVO("Student", "学生");
		setLayout(null);
		setBackground(Color.white);
		for (int i = 0; i < FUNC_NUM; i++) {
			fBtn[i] = new FunctionBtn(FUNC_BTN_NAME[i]);
			fbar.add(fBtn[i]);
		}

		sp.setLocation(30, 70);
		userTypecb.setBounds(3, 3, 150, 24);
		userTypecb.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		refreshBtn.setBounds(155, 3, 24, 24);
		sp.getTopPanel().setLayout(null);
		sp.getTopPanel().add(userTypecb);
		sp.getTopPanel().add(refreshBtn);

		userTypecb.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		for (int i = 0; i < ut.length; i++) {
			userTypecb.addItem(ut[i]);
		}

		scrollp.getViewport().setBackground(Color.white);
		scrollp.setViewportView(table);
		sp.getCenterPanel().setLayout(new BorderLayout());
		sp.getCenterPanel().add(scrollp, BorderLayout.CENTER);

		setListener();
		add(fbar);
		add(sp);

		fBtn[0].setEnabled(false);
		fBtn[1].setEnabled(false);
		fBtn[2].setEnabled(false);
		fBtn[3].setEnabled(false);
		fBtn[4].setEnabled(false);
	}

	/* *
	 * 显示表格
	 */
	private void showTable() {
		head = null;
		content = null;
		table.clearSelection();
		dtm = new DefaultTableModel(0, 5);
		table.setModel(dtm);
		table.updateUI();
		String listName = ((UserTypeVO) userTypecb.getSelectedItem()).name_en
				.toLowerCase() + "_list";
		Object fb = logic.showInfoTableHead(listName);
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, ((Feedback) fb).getContent());
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showInfoTableContent(listName, "all");
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
		// 增加按钮监听
		fBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String itemName = null;
				if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Student")) {
					itemName = "student";
				} else {
					itemName = "teacher；"
							+ ((UserTypeVO) userTypecb.getSelectedItem()).name_en;
				}
				new AddUserUI(itemName);
			}
		});

		// 修改按钮监听
		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = null;
				if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Student")) {
					itemName = "student";
				} else if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Login")) {
					itemName = "user";
				} else {
					itemName = "teacher；"
							+ ((UserTypeVO) userTypecb.getSelectedItem()).name_en;
				}
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					String[] origin = new String[table.getColumnCount()];
					for (int i = 0; i < origin.length; i++) {
						origin[i] = (String) table.getValueAt(selectRowNum, i);
					}
					System.out.println(itemName);
					new EditUserUI(itemName, origin);
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR.getContent());
				}
			}
		});

		// 删除按钮监听
		fBtn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = null;
				if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Student")) {
					itemName = "student";
				} else if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Login")) {
					itemName = "user";
				} else {
					itemName = "teacher";
				}
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					System.out.println(table.getValueAt(selectRowNum, 0));
					Feedback fb = logic.delUser(itemName,
							(String) table.getValueAt(selectRowNum, 0));
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR.getContent());
				}
			}
		});

		// 导入按钮监听
		fBtn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String itemName = null;
				if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Student")) {
					itemName = "student";
				} else if (((UserTypeVO) userTypecb.getSelectedItem()).name_en
						.equals("Login")) {
					itemName = "user";
				} else {
					itemName = "teacher";
				}
				new ImportUI(itemName, head);
			}
		});

		// 导出按钮监听
		fBtn[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ExportUI(head, content);
			}
		});

		userTypecb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String listName = ((UserTypeVO) userTypecb.getSelectedItem()).name_en;
				if ((userTypecb.getSelectedIndex() == 0)) {
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(false);
					fBtn[2].setEnabled(false);
					fBtn[3].setEnabled(true);
					fBtn[4].setEnabled(true);
				} else if (listName.startsWith("null")) {
					JOptionPane.showMessageDialog(null, "请选择用户类型。。。");
				} else if (listName.startsWith("Login")) {
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(true);
					fBtn[2].setEnabled(false);
					fBtn[3].setEnabled(false);
					fBtn[4].setEnabled(true);
					showTable();
				} else {
					fBtn[0].setEnabled(true);
					fBtn[1].setEnabled(true);
					fBtn[2].setEnabled(true);
					fBtn[3].setEnabled(true);
					fBtn[4].setEnabled(true);
					showTable();
				}
			}
		});

		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (userTypecb.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择用户类型。。。");
				} else {
					showTable();
				}
			}
		});

	}
}
