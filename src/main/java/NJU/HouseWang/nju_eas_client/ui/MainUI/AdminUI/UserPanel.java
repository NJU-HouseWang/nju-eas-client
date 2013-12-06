package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import NJU.HouseWang.nju_eas_client.ui.SubUI.AddItemUI;
import NJU.HouseWang.nju_eas_client.ui.SubUI.EditItemUI;
import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;
import NJU.HouseWang.nju_eas_client.vo.UserTypeVO;

@SuppressWarnings("serial")
public class UserPanel extends JPanel {
	private static int FUNC_NUM = 5;// 功能按钮的数量
	private static String[] FUNC_BTN_NAME = { "AddBtn", "ModifyBtn", "DelBtn",
			"ImportBtn", "ExportBtn" };// 功能按钮的名称
	private UserTypeVO[] ut = new UserTypeVO[5];// 用户类型列表
	private AdminUILogic logic = new AdminUILogic();// 管理员界面的逻辑
	private JPanel fbar = new FunctionBar();// 功能按钮栏
	private FunctionBtn[] fBtn = new FunctionBtn[FUNC_NUM];// 功能按钮
	private SubPanel sp = new SubPanel("", 740, 380);// 子界面
	private JComboBox<UserTypeVO> userTypecb = new JComboBox<UserTypeVO>();// 用户类型下拉框
	private JButton refreshBtn = new JButton();// 刷新按钮
	private JTextField conditiontf = new JTextField();// 条件输入框
	private JButton searchBtn = new JButton();// 搜索按钮

	private JScrollPane scrollp = new JScrollPane();// 表格滚动框
	private DefaultTableModel dtm = new DefaultTableModel(20, 5);// 表格Model
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
		ut[0] = new UserTypeVO("Admin", "管理员");
		ut[1] = new UserTypeVO("SchoolDean", "学校教务老师");
		ut[2] = new UserTypeVO("DeptAD", "院系教务老师");
		ut[3] = new UserTypeVO("Teacher", "任课老师");
		ut[4] = new UserTypeVO("Student", "学生");
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
		conditiontf.setBounds(562, 4, 150, 22);
		conditiontf.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		conditiontf.setBorder(null);
		searchBtn.setBounds(712, 4, 22, 22);
		searchBtn.setBorder(null);
		sp.getTopPanel().setLayout(null);
		sp.getTopPanel().add(userTypecb);
		sp.getTopPanel().add(refreshBtn);
		sp.getTopPanel().add(conditiontf);
		sp.getTopPanel().add(searchBtn);

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
	}

	/* *
	 * 显示表格
	 */
	private void showTable() {
		head = null;
		content = null;
		String listName = ((UserTypeVO) userTypecb.getSelectedItem()).name_en
				+ "_list";
		Object fb = logic.showTableHead(listName);
		if (fb instanceof Feedback) {
			JOptionPane.showMessageDialog(null, fb);
		} else if (fb instanceof String[]) {
			head = (String[]) fb;
			fb = logic.showTableContent(listName);
			if (fb instanceof Feedback) {
				JOptionPane.showMessageDialog(null, fb);
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
				if (userTypecb.getSelectedIndex() == 4) {
					new AddItemUI("studnet", head);
				} else {
					new AddItemUI("teacher", head);
				}
			}
		});

		// 修改按钮监听
		fBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = null;
				if (userTypecb.getSelectedIndex() == 4) {
					itemName = "student";
				} else {
					itemName = "teacher";
				}
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					String[] origin = new String[table.getColumnCount()];
					for (int i = 0; i < origin.length; i++) {
						origin[i] = (String) table.getValueAt(selectRowNum, i);
					}
					new EditItemUI(itemName, head, origin);
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR);
				}
			}
		});

		// 删除按钮监听
		fBtn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = null;
				if (userTypecb.getSelectedIndex() == 4) {
					itemName = "student";
				} else {
					itemName = "teacher";
				}
				int selectRowNum = table.getSelectedRow();

				if (table.getSelectedRowCount() == 1 && selectRowNum != -1) {
					System.out.println(table.getValueAt(selectRowNum, 0));
					logic.delItem(itemName,
							(String) table.getValueAt(selectRowNum, 0));
				} else {
					JOptionPane.showMessageDialog(null,
							Feedback.SELECTION_ERROR);
				}
			}
		});

		userTypecb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable();
				String listName = ((UserTypeVO) userTypecb.getSelectedItem()).name_en;
				if (listName.equals("Admin")) {
					fBtn[0].setEnabled(false);
					fBtn[1].setEnabled(false);
					fBtn[2].setEnabled(false);
					fBtn[3].setEnabled(false);
					fBtn[4].setEnabled(false);
				} else {
					fBtn[0].setEnabled(true);
					fBtn[1].setEnabled(true);
					fBtn[2].setEnabled(true);
					fBtn[3].setEnabled(true);
					fBtn[4].setEnabled(true);
				}
			}
		});

		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTable();
			}
		});

	}
}
