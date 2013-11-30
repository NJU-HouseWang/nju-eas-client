package NJU.HouseWang.nju_eas_client.ui.SubUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;

public class AddEduFwFrame extends JFrame {
	// 窗口位置、大小信息
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 630;
	private int hight = 500;

	// 窗口内包含的Panel
	private JPanel topPanel = null;
	private JPanel mainPanel = null;
	private JPanel bottomPanel = null;

	// topPanel内的元件
	private JLabel stepNum = null;
	private JLabel stepInfo = null;

	// mainPanel内的元件
	private CardLayout cl = null;
	private int index = 1;
	private JPanel page1 = null;
	private JPanel page2 = null;
	private JPanel page3 = null;
	private JPanel page4 = null;
	private JTextField numtf = new JTextField(8);
	private JPanel modulep = null;
	private Component[][] moduleTable = null;
	private DefaultTableModel[] typeDtm = null;
	private JTable[] typeTable = null;
	private JScrollPane[] tableScroll = null;
	private DefaultTableModel[] couDtm = null;
	private JTable[] couTable = null;
	private JScrollPane[] couScroll = null;

	private JButton lastbtn = null;
	private JButton nextbtn = null;
	private JButton cancelbtn = null;
	// efw的信息
	private final static int MAX_MODULE_NUM = 8;
	private int moduleNum = 0;
	private ArrayList<String> moduleInfo = new ArrayList<String>();
	private ArrayList<String> typeInfo = new ArrayList<String>();
	private ArrayList<String> courseInfo = new ArrayList<String>();

	public AddEduFwFrame() {
		// 窗口位置和大小
		setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - hight) / 2 - 30, width, hight);
		setResizable(false);

		// 窗口标题
		setTitle("教学计划模板 - 设置向导");

		// 设置Layout
		setLayout(new BorderLayout());

		// topPanel配置
		topPanel = new JPanel();
		topPanel.setBackground(Color.darkGray);
		topPanel.setPreferredSize(new Dimension(getWidth(), 60));
		((FlowLayout) topPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		((FlowLayout) topPanel.getLayout()).setAlignOnBaseline(true);

		// topPanel元件配置
		stepNum = new JLabel("   STEP 1  ");
		stepNum.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		stepNum.setForeground(Color.WHITE);
		topPanel.add(stepNum);

		stepInfo = new JLabel("填写模块信息");
		stepInfo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		stepInfo.setForeground(Color.WHITE);
		topPanel.add(stepInfo);

		// bottomPanel配置
		bottomPanel = new JPanel(null);
		bottomPanel.setPreferredSize(new Dimension(getWidth(), 60));
		JSeparator sprt = new JSeparator();
		sprt.setBounds(0, 0, getWidth(), 1);
		sprt.setForeground(Color.DARK_GRAY);
		bottomPanel.add(sprt);

		lastbtn = new JButton("上一步");
		lastbtn.setBounds(300, 18, 90, 25);
		lastbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (index > 1) {
					index--;
					cl.show(mainPanel, "" + index);
					if (index == 1) {
						lastbtn.setEnabled(false);
					} else {
						lastbtn.setEnabled(true);
					}
				}
			}
		});
		lastbtn.setEnabled(false);
		nextbtn = new JButton("下一步");
		nextbtn.setBounds(405, 18, 90, 25);
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((JButton) e.getSource()).getText().equals("完成")) {
					// 这里发送命令
				} else {
					if (collectInfo()) {
						index++;
						cl.show(mainPanel, "" + index);
						if (index == 1) {
							lastbtn.setEnabled(false);
						} else {
							lastbtn.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "您在该页面的输入有误");
					}
				}
			}
		});
		cancelbtn = new JButton("取消");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "放弃创建？", "放弃创建？",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		cancelbtn.setBounds(510, 18, 90, 25);
		bottomPanel.add(lastbtn);
		bottomPanel.add(nextbtn);
		bottomPanel.add(cancelbtn);

		// mainPanel配置
		mainPanel = new JPanel(null);
		mainPanel.setBackground(Color.white);
		cl = new CardLayout();
		mainPanel.setLayout(cl);

		// mainPanel////////////////////////////////////////////////////////////////
		page1 = new Page1Panel();
		mainPanel.add(page1, "1");
		// /////////////////////////////////////////////////////////////////////////

		cl.show(mainPanel, "1");
		// 加入Panel
		add(topPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// 设置默认关闭操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 窗口可见性
		setVisible(true);
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
			repaint();
		} catch (NumberFormatException excep) {
			JOptionPane.showMessageDialog(null, "您输入的数字格式不正确！");
			numtf.setText("");
		}
	}

	public boolean collectInfo() {
		boolean r;
		switch (index) {
		case 1:
			r = collectModuleInfo();
			if (r) {
				page2 = new Page2Panel();
				mainPanel.add(page2, "2");
			}
			break;
		case 2:
			r = collectTypeInfo();
			if (r) {
				page3 = new Page3Panel();
				mainPanel.add(page3, "3");
			}
			break;
		case 3:
			r = collectCourseInfo();
			if (r) {
				page4 = new Page4Panel();
				mainPanel.add(page4, "4");
			}
			break;
		default:
			r = false;
		}
		return r;
	}

	public boolean collectModuleInfo() {
		moduleInfo.clear();
		for (int i = 1; i <= moduleNum; i++) {
			String infoLine = "模块" + i + "；";
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
				if (s.split("；")[3].compareTo(s.split("；")[2]) < 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean collectTypeInfo() {
		typeInfo.clear();
		for (int i = 0; i < moduleNum; i++) {
			int rowPoint = 0;
			while (true) {
				String line = "";
				for (int j = 0; j < 7; j++) {
					String cell = null;
					if (typeTable[i].getValueAt(rowPoint, j) != null) {
						cell = (String) typeTable[i].getValueAt(rowPoint, j);
						line += cell + "；";
					} else {
						line = null;
						break;
					}
				}
				if (line != null) {
					typeInfo.add(new String(moduleInfo.get(i)) + line);
				} else {
					break;
				}
				rowPoint++;
			}
		}
		return true;
	}

	public boolean collectCourseInfo() {
		courseInfo.clear();
		for (int i = 0; i < typeInfo.size(); i++) {
			int rowPoint = 0;
			while (true) {
				String line = "";
				for (int j = 0; j < 3; j++) {
					String cell = null;
					if (couTable[i].getValueAt(rowPoint, j) != null) {
						cell = (String) couTable[i].getValueAt(rowPoint, j);
						line += cell + "；";
					} else {
						line = null;
						break;
					}
				}
				if (line != null) {
					courseInfo.add(new String(typeInfo.get(i)) + line);
				} else {
					if(i == 0 || (!courseInfo.get(courseInfo.size()).contains(typeInfo.get(i))))
					courseInfo.add(new String(typeInfo.get(i)));
					break;
				}
				rowPoint++;
			}
		}
		return true;
	}

	class Page1Panel extends JPanel {
		JPanel nump = new JPanel();

		public Page1Panel() {
			setLayout(null);
			nump.setBounds(50, 10, 520, 40);
			((FlowLayout) nump.getLayout()).setHgap(20);
			JLabel numlb = new JLabel("请输入模块个数(≤8):");
			numlb.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			numtf.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			JButton numConfirm = new JButton("确  定");
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
			modulep = new JPanel();
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
	}

	class Page2Panel extends JPanel {
		JPanel p2main = new JPanel();
		JScrollPane p2sp = new JScrollPane(p2main);

		JLabel[] moduleLb = new JLabel[moduleNum];

		public Page2Panel() {
			typeDtm = new DefaultTableModel[moduleNum];
			typeTable = new JTable[moduleNum];
			tableScroll = new JScrollPane[moduleNum];
			// 设置模块外的信息
			stepNum.setText("   STEP 2  ");
			stepInfo.setText("填写类别信息");
			// 设置模块内的信息
			setLayout(new BorderLayout());
			p2sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			p2sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			p2main.setPreferredSize(new Dimension(595, moduleNum * 200));
			p2main.setLayout(new BoxLayout(p2main, BoxLayout.Y_AXIS));
			for (int i = 0; i < moduleNum; i++) {
				typeDtm[i] = new DefaultTableModel(26, 7);
				typeDtm[i].setColumnIdentifiers(new String[] { "课程性质", "序列",
						"课程类型", "最低学分", "最高学分","起始学期", "终止学期" });
				char c = 'A';
				for (int j = 0; j < 26; j++) {
					typeDtm[i].setValueAt((char) (c + j) + "", j, 1);
				}
				moduleLb[i] = new JLabel(moduleInfo.get(i), JLabel.LEFT);
				moduleLb[i].setFont(new Font("微软雅黑", Font.PLAIN, 20));
				moduleLb[i].setPreferredSize(new Dimension(595, 50));
				p2main.add(moduleLb[i]);
				typeTable[i] = new JTable(typeDtm[i]);
				typeTable[i].setPreferredScrollableViewportSize(new Dimension(
						400, 150));
				DefaultTableCellRenderer r = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				typeTable[i].setDefaultRenderer(Object.class, r);
				typeTable[i].setRowHeight(25);
				tableScroll[i] = new JScrollPane(typeTable[i]);
				p2main.add(tableScroll[i]);
			}

			add(p2sp, BorderLayout.CENTER);
		}
	}

	class Page3Panel extends JPanel {
		int typeNum = typeInfo.size();

		JPanel p3main = new JPanel();
		JScrollPane p3sp = new JScrollPane(p3main);
		JLabel[] couLb = new JLabel[typeNum];

		public Page3Panel() {
			couDtm = new DefaultTableModel[typeNum];
			couTable = new JTable[typeNum];
			couScroll = new JScrollPane[typeNum];
			// 设置模块外的信息
			stepNum.setText("   STEP 3  ");
			stepInfo.setText("填写课程信息");
			// 设置模块内的信息
			setLayout(new BorderLayout());
			p3sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			p3sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			p3main.setPreferredSize(new Dimension(595, typeNum * 200));
			p3main.setLayout(new BoxLayout(p3main, BoxLayout.Y_AXIS));
			for (int i = 0; i < typeNum; i++) {
				couDtm[i] = new DefaultTableModel(16, 3);
				couDtm[i].setColumnIdentifiers(new String[] { "课程名称", "最低学分",
						"最高学分" });
				couLb[i] = new JLabel(typeInfo.get(i), JLabel.LEFT);
				couLb[i].setFont(new Font("微软雅黑", Font.PLAIN, 20));
				couLb[i].setPreferredSize(new Dimension(595, 50));
				p3main.add(couLb[i]);
				couTable[i] = new JTable(couDtm[i]);
				couTable[i].setPreferredScrollableViewportSize(new Dimension(
						400, 150));
				DefaultTableCellRenderer r = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				couTable[i].setDefaultRenderer(Object.class, r);
				couTable[i].setRowHeight(25);
				couScroll[i] = new JScrollPane(couTable[i]);
				p3main.add(couScroll[i]);
			}

			add(p3sp, BorderLayout.CENTER);

		}
	}

	class Page4Panel extends JPanel {
		public Page4Panel() {
			// 设置模块外的信息
			stepNum.setText("   FINAL STEP  ");
			stepInfo.setText("上传/确认信息");
			nextbtn.setText("完成");
			// 
			for(String s:courseInfo) {
				System.out.println(s);
			}
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			add(new JLabel("正在提交..."));
			try {
//				sendAddEduFwCommand();
			} catch (Exception e) {
				e.printStackTrace();
				add(new JLabel("提交出现问题..."));
				add(new JButton("重试"));
			}
		}
	}

	public void sendAddEduFwCommand() throws Exception {
		String command = "add；eduframework";
			ClientPool cPool = ClientPool.getInstance();
			NetService net = cPool.getClient();
			net.sendCommand(command);
			net.sendList(courseInfo);
			showFeedBack(net.receiveFeedback());
	}
	
	public void showFeedBack(String fbStr) {
		Feedback feedback = Feedback.valueOf(fbStr);
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}

	public void showFeedBack(Feedback feedback) {
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}
	
	public static void main(String[] args) {
		new AddEduFwFrame();
	}
}
