package NJU.HouseWang.nju_eas_client.ui.MainUI.SchoolDeanUI.AddFwGuideUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import NJU.HouseWang.nju_eas_client.ui.CommonUI.Label.WaitingLabel;
import NJU.HouseWang.nju_eas_client.uiLogic.SchoolDeanUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class CreateEduFwUI {
	private SchoolDeanUILogic slogic = new SchoolDeanUILogic();

	private JFrame frame = new JFrame();
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
	private JPanel[] page = new JPanel[4];

	private JButton lastbtn = null;
	private JButton nextbtn = null;
	private JButton cancelbtn = null;
	private WaitingLabel lb = new WaitingLabel();
	private int index = 0;

	public CreateEduFwUI() {
		// 窗口位置和大小
		frame.setBounds(((int) screenSize.getWidth() - width) / 2,
				((int) screenSize.getHeight() - hight) / 2 - 30, width, hight);
		frame.setResizable(false);

		// 窗口标题
		frame.setTitle("教学框架策略 - 设置向导");

		// 设置Layout
		frame.setLayout(new BorderLayout());

		// topPanel配置
		topPanel = new JPanel();
		topPanel.setBackground(Color.darkGray);
		topPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
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
		bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
		JSeparator sprt = new JSeparator();
		sprt.setBounds(0, 0, frame.getWidth(), 1);
		sprt.setForeground(Color.DARK_GRAY);
		bottomPanel.add(sprt);

		lastbtn = new JButton("上一步");
		lastbtn.setBounds(300, 18, 90, 25);
		lastbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (index > 0) {
					index--;
					cl.show(mainPanel, "" + index);
					if (index == 0) {
						lastbtn.setEnabled(false);
					} else {
						lastbtn.setEnabled(true);
					}
					if (nextbtn.getText().equals("完成")) {
						nextbtn.setText("下一步");
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
					uploadEduFw();
				} else {
					if (collectInfo()) {
						index++;
						cl.show(mainPanel, "" + index);
						if (index == 0) {
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
					frame.dispose();
				}
			}
		});
		cancelbtn.setBounds(510, 18, 90, 25);
		bottomPanel.add(lastbtn);
		bottomPanel.add(nextbtn);
		bottomPanel.add(cancelbtn);
		lb.setBounds(430, 8, 42, 42);
		bottomPanel.add(lb);
		lb.setVisible(false);

		// mainPanel配置
		mainPanel = new JPanel(null);
		mainPanel.setBackground(Color.white);
		cl = new CardLayout();
		mainPanel.setLayout(cl);
		page[0] = new ModulePanel();
		mainPanel.add(page[0], "0");
		cl.show(mainPanel, "0");
		// 加入Panel
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		// 设置默认关闭操作
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 窗口可见性
		frame.setVisible(true);

	}

	public boolean collectInfo() {
		boolean r;
		switch (index) {
		case 0:
			r = ((ModulePanel) page[0]).collectModuleInfo();
			if (r) {
				page[1] = new TypePanel(
						((ModulePanel) page[0]).getModuleInfoList());
				mainPanel.add(page[1], "1");
				stepNum.setText("   STEP 2  ");
				stepInfo.setText("填写类别信息");
			}
			break;
		case 1:
			r = ((TypePanel) page[1]).collectTypeInfo();
			if (r) {
				page[2] = new CoursePanel(
						((TypePanel) page[1]).getTypeInfoList());
				mainPanel.add(page[2], "2");
				stepNum.setText("   STEP 3  ");
				stepInfo.setText("填写课程信息");
			}
			break;
		case 2:
			r = ((CoursePanel) page[2]).collectCourseInfo();
			if (r) {
				page[3] = new CommitPanel(
						((CoursePanel) page[2]).getCourseInfoList());
				mainPanel.add(page[3], "3");
				stepNum.setText("   FINAL STEP  ");
				stepInfo.setText("上传/确认信息");
				nextbtn.setText("完成");
			}
			break;
		default:
			r = false;
		}
		return r;
	}

	private void uploadEduFw() {
		nextbtn.setVisible(false);
		cancelbtn.setEnabled(false);
		lb.setVisible(true);
		new Thread(new Runnable() {
			public void run() {
				Feedback fb = ((CommitPanel) page[3]).uploadEduFw(nextbtn,
						cancelbtn, lb);
				if (fb == Feedback.OPERATION_SUCCEED) {
					frame.setVisible(false);
					frame.dispose();
				}
			}
		}).start();
	}
}
