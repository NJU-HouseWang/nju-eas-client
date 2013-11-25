/*
 * 文件名：AdminUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-11-02
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.launcher.ClientLauncher;
import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.SubUI.AddItemFrame;
import NJU.HouseWang.nju_eas_client.ui.SubUI.EditItemFrame;
import NJU.HouseWang.nju_eas_client.uiService.UIService;

/*
 * 类：AdminUI
 * 
 */
public class AdminFrame extends CommonFrame implements UIService {
	private ClientPool cPool = null;
	private TitleBar tbar = null;
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private JComboBox<String> listChooser = null;
	private JTextField searchtf = null;
	private JButton searchBtn = null;

	private JPanel tablep = null;
	private JScrollPane scrollp = null;
	private DefaultTableModel dtm = null;
	private JTable table = null;

	private String userName = null;
	private String[] head = null;
	private String[][] content = null;

	public AdminFrame(String userName) {
		super("AdminFrame");
		this.userName = userName;
		tbar = new TitleBar(userName);
		fbar = new FunctionBar();
		fbar.setLocation(30, 100);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		fBtn[2] = new FunctionBtn("ModifyBtn");
		fBtn[3] = new FunctionBtn("ImportBtn");
		fBtn[4] = new FunctionBtn("ExportBtn");
		listChooser = new JComboBox<String>();
		searchtf = new JTextField();
		searchBtn = new JButton();
		listChooser.setBounds(166, 167, 134, 22);
		listChooser.addItem("user_list");
		listChooser.addItem("log_list");
		listChooser.addItem("states_list");

		searchtf.setBounds(627, 168, 130, 20);
		searchtf.setBorder(null);

		searchBtn.setBounds(759, 167, 22, 22);

		tablep = new JPanel();
		tablep.setBounds(72, 210, 712, 381);
		tablep.setLayout(null);
		scrollp = new JScrollPane();
		dtm = new DefaultTableModel(30, 5);
		table = new JTable(dtm);
		scrollp.setViewportView(table);
		scrollp.setSize(tablep.getSize());
		tablep.add(scrollp);

		for (int i = 0; i < 5; i++) {
			fbar.add(fBtn[i]);
		}
		add(tbar);
		add(fbar);
		add(listChooser);
		add(searchtf);
		add(searchBtn);
		add(tablep);
		setListener();
		setVisible(true);
		try {
			cPool = ClientPool.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			showFeedback(Feedback.INTERNET_ERROR);
		} finally {
			dispose();
			ClientLauncher.createUI("Login", null);
		}
	}

	private void setListener() {
		listChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showList((String) listChooser.getSelectedItem(),
						searchtf.getText());
				table.updateUI();
			}
		});
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showList((String) listChooser.getSelectedItem(),
						searchtf.getText());
				table.updateUI();
			}
		});
		fBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				new AddItemFrame(listName, head);
			}
		});
		fBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				new EditItemFrame(listName, head);
			}
		});
		fBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String listName = (String) listChooser.getSelectedItem();
				new EditItemFrame(listName, head);
			}
		});
	}

	public void showList(String listName, String conditions) {
		ArrayList<String> l1 = null;
		String command1 = "show；" + listName + "_head";
		try {
			NetService ns1 = cPool.getClient();
			ns1.sendCommand(command1);
			l1 = ns1.receiveList();
			head = (String[]) l1.toArray();
		} catch (Exception e) {
			e.printStackTrace();
			showFeedback(Feedback.INTERNET_ERROR);
		}

		ArrayList<String> l2 = null;
		String command2 = "show；" + listName;
		try {
			NetService ns2 = cPool.getClient();
			ns2.sendCommand(command2);
			l2 = ns2.receiveList();
			content = new String[l2.size()][head.length];
			for (int i = 0; i < l2.size(); i++) {
				content[i] = l2.get(i).split("；");
			}
		} catch (Exception e) {
			e.printStackTrace();
			showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	public void delUser(String listName, String id) {
		String command = "del；user；" + content;
		try {
			NetService ns = cPool.getClient();
			ns.sendCommand(command);
			showFeedback(ns.receiveCommand());
			ns.shutDownConnection();
		} catch (Exception e) {
			e.printStackTrace();
			showFeedback(Feedback.INTERNET_ERROR);
		}
	}

	public void showFeedback(String fbStr) {
		showFeedback(Feedback.valueOf(fbStr));
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}

	public static void main(String[] args) {
		new AdminFrame("Admin");
	}
}
