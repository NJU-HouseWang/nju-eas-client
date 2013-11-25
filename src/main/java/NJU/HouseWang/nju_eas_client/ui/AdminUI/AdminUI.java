/*
 * 文件名：AdminUI.java
 * 创建者：王鑫
 * 创建时间：2013-10-16
 * 最后修改：王鑫
 * 修改时间：2013-11-02
 */
package NJU.HouseWang.nju_eas_client.ui.AdminUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FormPanel.FormPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.uiController.AdminUIController;
import NJU.HouseWang.nju_eas_client.uiService.UIService;

/*
 * 类：AdminUI
 * 
 */
public class AdminUI implements UIService {
	private CommonFrame frame = null;
	private String userName = null;
	private AdminUIController uic = null;
	private TitleBar tbar = null;
	private FunctionBar fbar = null;
	private FunctionBtn[] fBtn = new FunctionBtn[5];
	private JComboBox<String> listChooser = null;
	private JTextField searchtf = null;
	private JButton searchBtn = null;
	private FormPanel formp = null;

	public void setListener() {
		listChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uic.showListHead((String)listChooser.getSelectedItem());
				uic.showList((String)listChooser.getSelectedItem(), searchtf.getText());
			}
		});
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uic.showList((String)listChooser.getSelectedItem(), searchtf.getText());
			}
		});
		fBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	public AdminUI(AdminUIController uic) {
		this.uic = uic;
	}

	public void create() {
		frame = new CommonFrame("AdminFrame");
		tbar = new TitleBar(userName);
		fbar = new FunctionBar();
		fbar.setLocation(30, 100);
		fBtn[0] = new FunctionBtn("AddBtn");
		fBtn[1] = new FunctionBtn("DelBtn");
		fBtn[2] = new FunctionBtn("ModifyBtn");
		fBtn[3] = new FunctionBtn("ImportBtn");
		fBtn[4] = new FunctionBtn("ExportBtn");
		for (int i = 0; i < 5; i++) {
			fbar.add(fBtn[i]);
		}
		listChooser = new JComboBox<String>();
		listChooser.setBounds(166, 167, 134, 22);
		listChooser.addItem("user_list");
		listChooser.addItem("log_list");
		listChooser.addItem("states_list");

		searchtf = new JTextField();
		searchtf.setBounds(627, 168, 130, 20);
		searchtf.setBorder(null);

		searchBtn = new JButton();
		searchBtn.setBounds(759, 167, 22, 22);

		formp = new FormPanel();
		formp.setBounds(72, 210, 712, 381);

		frame.add(tbar);
		frame.add(fbar);
		frame.add(listChooser);
		frame.add(searchtf);
		frame.add(searchBtn);
		frame.add(formp);
		frame.setVisible(true);
	}

	

	@Override
	public void dispose() {
		
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(frame, feedback.getContent());
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public void setFormHead(String[] head) {
		formp.setFormHead(head);
	}

	public void setForm(String[][] content) {
		formp.setFormContent(content);
	}

	public String[][] getForm() {
		String[] head = formp.getHead();
		String[][] content = formp.getContent();
		String[][] form = new String[content.length + 1][head.length];
		form[0] = head;
		for (int i = 1; i < form.length; i++) {
			form[i] = content[i - 1];
		}
		return form;
	}

}
