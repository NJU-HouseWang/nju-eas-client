package NJU.HouseWang.nju_eas_client.ui.SubUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AddTpFrame extends JFrame {
	private final int PAGE_NUM = 2;
	private int index = 1;

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
	private JPanel[] pagePanel = new JPanel[PAGE_NUM];
	private CardLayout cl = new CardLayout();

	// bottom内的元件
	private JButton lastbtn = null;
	private JButton nextbtn = null;
	private JButton cancelbtn = null;

	public AddTpFrame() {
		// 窗口位置和大小
		setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - hight) / 2 - 30, width, hight);
		setResizable(false);

		// 窗口标题
		setTitle("教学计划 - 设置向导向导");

		// 设置Layout
		setLayout(new BorderLayout());

		// topPanel配置
		topPanel = new JPanel();
		topPanel.setBackground(Color.darkGray);
		topPanel.setPreferredSize(new Dimension(getWidth(), 60));
		((FlowLayout) topPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		((FlowLayout) topPanel.getLayout()).setAlignOnBaseline(true);

		// topPanel元件配置
		stepNum = new JLabel("   STEP 0  ");
		stepNum.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		stepNum.setForeground(Color.WHITE);
		topPanel.add(stepNum);

		stepInfo = new JLabel("这里是步骤说明");
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
					if (processCurrentPageInfo()) {
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
		pagePanel[0] = new InputPanel();
		pagePanel[1] = new ConfirmPanel();

		for (int i = 0; i < PAGE_NUM; i++) {
			mainPanel.add(pagePanel[i], (i + 1) + "");
		}
		cl.show(mainPanel, index + "");
		add(topPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// 设置默认关闭操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 窗口可见性
		setVisible(true);
	}

	public ArrayList<String> getEduFwInformation() {
		return new ArrayList<String>();
	}

	public int getCourseTypeNum() {
		return 0;
	}

	private boolean processCurrentPageInfo() {
		return true;
	}

	class InputPanel extends JPanel {

		// 课程模块的信息
		int typeNum = getCourseTypeNum();
		ArrayList<String> eduFw = getEduFwInformation();

		// 课程列表的表格
		DefaultTableModel[] couDtm = null;
		JTable[] couTable = null;
		JScrollPane[] couScroll = null;

		JPanel p3main = new JPanel();
		JScrollPane p3sp = new JScrollPane(p3main);
		JLabel[] couLb = new JLabel[typeNum];

		public InputPanel() {
			setBackground(Color.blue);
			couDtm = new DefaultTableModel[typeNum];
			couTable = new JTable[typeNum];
			couScroll = new JScrollPane[typeNum];
			// 设置模块外的信息
			stepNum.setText("   STEP 1  ");
			stepInfo.setText("填写计划课程");
			// 设置模块内的信息
			setLayout(new BorderLayout());
			p3sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			p3sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			p3main.setPreferredSize(new Dimension(595, typeNum * 200));
			p3main.setLayout(new BoxLayout(p3main, BoxLayout.Y_AXIS));
			for (int i = 0; i < typeNum; i++) {
				couDtm[i] = new DefaultTableModel(16, 3);
				couDtm[i].setColumnIdentifiers(new String[] { "课程号", "课程名称",
						"学分", "学时", "上课学期" });
				couLb[i] = new JLabel(eduFw.get(i), JLabel.LEFT);
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

	class ConfirmPanel extends JPanel {
		public ConfirmPanel() {
			setBackground(Color.green);
		}
	}

//	public static void main(String[] args) {
//		new AddTpFrame();
//	}
}
