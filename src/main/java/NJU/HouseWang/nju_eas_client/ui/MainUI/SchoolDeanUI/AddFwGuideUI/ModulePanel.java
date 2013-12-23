package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModulePanel extends JPanel {
	private final static int MAX_MODULE_NUM = 8;
	private JPanel nump = new JPanel();
	private JTextField numtf = new JTextField(8);
	private JButton numConfirm = new JButton("确  定");
	private JPanel modulep = new JPanel();
	private Component[][] moduleTable = null;
	private int moduleNum = 0;

	private ArrayList<String> moduleInfo = new ArrayList<String>();

	public ModulePanel() {
		setLayout(null);
		nump.setBounds(50, 10, 520, 40);
		((FlowLayout) nump.getLayout()).setHgap(20);
		JLabel numlb = new JLabel("请输入模块个数(≤8):");
		numlb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		numConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		numConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createModuleTable();
			}
		});
		numtf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createModuleTable();
			}
		});
		modulep.setBounds(45, 60, 500, (MAX_MODULE_NUM + 1) * 30);
		GridLayout gl = new GridLayout(MAX_MODULE_NUM + 1, 4);

		modulep.setLayout(gl);
		gl.setHgap(10);
		gl.setVgap(5);
		moduleTable = new Component[MAX_MODULE_NUM + 1][4];
		moduleTable[0][0] = new JLabel("模块序号", JLabel.CENTER);
		moduleTable[0][1] = new JLabel("模块名称", JLabel.CENTER);
		moduleTable[0][2] = new JLabel("模块最低学分", JLabel.CENTER);
		moduleTable[0][3] = new JLabel("模块最高学分", JLabel.CENTER);
		for (int i = 1; i < MAX_MODULE_NUM + 1; i++) {
			moduleTable[i][0] = new JLabel("模块" + i, JLabel.CENTER);
			for (int j = 1; j < 4; j++) {
				moduleTable[i][j] = new JTextField();
			}
		}
		for (int i = 0; i < MAX_MODULE_NUM + 1; i++) {
			for (int j = 0; j < 4; j++) {
				moduleTable[i][j].setFont(new Font("微软雅黑", Font.PLAIN, 12));
				moduleTable[i][j].setVisible(false);
				modulep.add(moduleTable[i][j]);
			}
		}
		nump.add(numlb);
		nump.add(numtf);
		nump.add(numConfirm);
		add(modulep);
		add(nump);
	}

	public void createModuleTable() {
		try {
			moduleNum = Integer.parseInt(numtf.getText());
			if (moduleNum < 1 || moduleNum > MAX_MODULE_NUM) {
				JOptionPane.showMessageDialog(null, "模块数量超出范围！");
				numtf.setText("");
			} else {
				for (int i = 0; i <= moduleNum; i++) {
					for (int j = 0; j < 4; j++) {
						moduleTable[i][j].setVisible(true);
					}
				}
				for (int i = moduleNum + 1; i < MAX_MODULE_NUM + 1; i++) {
					for (int j = 0; j < 4; j++) {
						moduleTable[i][j].setVisible(false);
					}
				}
			}
			validate();
		} catch (NumberFormatException excep) {
			JOptionPane.showMessageDialog(null, "您输入的数字格式不正确！");
			numtf.setText("");
		}
	}

	public boolean collectModuleInfo() {
		moduleInfo.clear();
		for (int i = 1; i <= moduleNum; i++) {
			String infoLine = i + "；";
			for (int j = 1; j < 4; j++) {
				String s = null;
				if (!(s = ((JTextField) moduleTable[i][j]).getText())
						.equals("")) {
					infoLine += s + "；";
				} else {
					return false;
				}
			}
			System.out.println("Get Module Info:" + infoLine);
			moduleInfo.add(infoLine);
			for (String s : moduleInfo) {
				if (Integer.parseInt(s.split("；")[2]) > Integer.parseInt((s
						.split("；")[3]))) {
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<String> getModuleInfoList() {
		return moduleInfo;
	}

	public int getModuleNum() {
		return moduleNum;
	}
}
