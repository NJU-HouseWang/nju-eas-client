/*
 * 文件名：SchoolDeanUI.java
 * 创建者：王晔
 * 创建时间：2013-10-18
 * 最后修改：王鑫
 * 修改时间：2013-10-30
 */
package NJU.HouseWang.nju_eas_client.ui.MainUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.CTable.CTable;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.BigMenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.CommonFrame;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.FunctionBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.MenuBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.SubPanel;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.Common.TitleBar;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn.FunctionBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.BigMenuBtn;
import NJU.HouseWang.nju_eas_client.ui.CommonUI.MenuBtn.MenuBtn;
import NJU.HouseWang.nju_eas_client.uiService.UIService;

/*
 * 类：SchoolDeanUI
 * 
 */
public class SchoolDeanUI extends CommonFrame implements UIService {
	private String userName = null;
	private TitleBar tbar = null;
	private MenuBar mbar = null;
	private MenuBtn[] mbtn = new MenuBtn[5];
	private String[] mbtnName = { "HomeBtn", "EduFrameworkBtn",
			"TeachingPlanBtn", "ShowInfoBtn", "CommonCourseBtn" };
	private JPanel[] childp = new JPanel[5];
	private JPanel cardp = new JPanel();
	private CardLayout card = new CardLayout();

	private String[][] edufwContent = new String[][] {
			{ "A", "V", "V", "V", "1", "1", "V" },
			{ "A", "C", "C", "C", "2", "2", "C" },
			{ "A", "C", "C", "C", "3", "3", "C" },
			{ "B", "V", "V", "V", "4", "4", "V" },
			{ "B", "F", "F", "F", "5", "5", "F" }

	};

	private String[] edufwHeader = new String[] { "as", "sdf", "sf", "sdf",
			"asdf", "adsf", "a" };

	public SchoolDeanUI(String userName) {
		super("SchoolFrame");
		this.userName = userName;
		tbar = new TitleBar(userName);
		mbar = new MenuBar();

		childp[0] = new HomePanel();
		childp[1] = new EduFrameworkPanel();
		childp[2] = new TeachingPlanPanel();
		childp[3] = new InfoListPanel();
		childp[4] = new CommonCoursePanel();
		// childp[5] = new PECoursePanel();

		cardp.setSize(800, 480);
		cardp.setLocation(30, 150);
		cardp.setLayout(card);

		for (int i = 0; i < mbtn.length; i++) {
			mbtn[i] = new MenuBtn(mbtnName[i]);
			mbar.add(mbtn[i]);
			childp[i].setSize(800, 480);
			cardp.add(childp[i], mbtnName[i]);
		}

		mbar.setLocation(30, 100);
		card.show(cardp, mbtnName[0]);
		mbtn[0].setSelected(true);
		add(cardp);
		add(mbar);
		add(tbar);
		setListener();
		setVisible(true);
	}

	public void setListener() {
		for (int i = 0; i < mbtn.length; i++) {
			mbtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(((MenuBtn) e.getSource()).getName());
					card.show(cardp, ((MenuBtn) e.getSource()).getName());
				}
			});
		}
	}

	public void showFeedback(String fbStr) {
		showFeedback(Feedback.valueOf(fbStr));
	}

	public void showFeedback(Feedback feedback) {
		JOptionPane.showMessageDialog(this, feedback.getContent());
	}

	public static void main(String[] args) {
		new SchoolDeanUI("陈建群");
	}

	class HomePanel extends JPanel {
		private BigMenuBtn[] bmbtn = new BigMenuBtn[4];
		private BigMenuBar bmbar = new BigMenuBar();

		public HomePanel() {
			for (int i = 0; i < bmbtn.length; i++) {
				bmbtn[i] = new BigMenuBtn(mbtnName[i + 1]);
				bmbar.add(bmbtn[i]);
			}
			bmbar.setLocation(25, 10);
			mbar.setLocation(30, 100);
			setBackground(Color.getHSBColor((float) 0.617, (float) 0.42,
					(float) 0.92));
			((FlowLayout) getLayout()).setAlignment(FlowLayout.LEFT);
			setLayout(null);
			add(bmbar);
			setListener();
		}

		void setListener() {
			for (int i = 0; i < mbtn.length - 1; i++) {
				bmbtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String bname = ((BigMenuBtn) e.getSource()).getName();
						card.show(cardp, bname);
						for (int i = 1; i < mbtn.length; i++) {
							if (mbtnName[i].equals(bname)) {
								mbtn[i].setSelected(true);
							}
						}
					}
				});
			}
		}
	}

	class EduFrameworkPanel extends JPanel {
		private FunctionBar fbar = null;
		private FunctionBtn[] fBtn = new FunctionBtn[2];
		private SubPanel edufwp = null;

		@SuppressWarnings("serial")
		public EduFrameworkPanel() {
			setLayout(null);
			setBackground(Color.white);
			fbar = new FunctionBar();
			fbar.setLocation(0, 0);
			fBtn[0] = new FunctionBtn("AddBtn");
			fBtn[1] = new FunctionBtn("DelBtn");
			for (int i = 0; i < fBtn.length; i++) {
				fbar.add(fBtn[i]);
			}
			add(fbar);

			edufwp = new SubPanel("当前框架策略", 740, 380);
			edufwp.setLocation(30, 70);

			EduFrameworkMap m = new EduFrameworkMap(edufwContent);
			DefaultTableModel tm = new DefaultTableModel(edufwContent.length,
					edufwContent[0].length) {
				public boolean isCellEditable(int indexRow, int indexColumn) {
					return false;
				}
			};
			for (int i = 0; i < edufwContent.length; i++) {
				for (int j = 0; j < edufwContent[i].length; j++) {
					tm.setValueAt(edufwContent[i][j], i, j);
				}
			}
			tm.setColumnIdentifiers(edufwHeader);

			CTable ct = new CTable(m, tm);
			ct.setEnabled(false);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			ct.setDefaultRenderer(Object.class, r);
			edufwp.getCenterPanel().setLayout(new BorderLayout());
			edufwp.getCenterPanel().add(new JScrollPane(ct),
					BorderLayout.CENTER);
			add(edufwp);

		}
	}

	class TeachingPlanPanel extends JPanel {
		private FunctionBar fbar = null;
		private FunctionBtn[] fBtn = new FunctionBtn[2];
		private SubPanel tpp = null;
		private SubPanel accessoryp = null;
		private SubPanel localStatuesp = null;
		private JComboBox<String> deptChooser = null;

		@SuppressWarnings("serial")
		public TeachingPlanPanel() {
			setLayout(null);
			setBackground(Color.white);
			fbar = new FunctionBar();
			fbar.setLocation(0, 0);
			fBtn[0] = new FunctionBtn("TrueBtn");
			fBtn[1] = new FunctionBtn("FalseBtn");
			for (int i = 0; i < fBtn.length; i++) {
				fbar.add(fBtn[i]);
			}
			add(fbar);

			deptChooser = new JComboBox<String>();
			deptChooser.setPreferredSize(new Dimension(120, 20));

			tpp = new SubPanel("教学计划  ", 500, 380);
			tpp.setLocation(30, 70);
			tpp.getTopPanel().add(deptChooser);

			accessoryp = new SubPanel("附件", 230, 150);
			accessoryp.setLocation(540, 70);

			localStatuesp = new SubPanel("当前状态", 230, 150);
			localStatuesp.setLocation(540, 230);

			EduFrameworkMap m = new EduFrameworkMap(edufwContent);
			DefaultTableModel tm = new DefaultTableModel(edufwContent.length,
					edufwContent[0].length) {
				public boolean isCellEditable(int indexRow, int indexColumn) {
					return false;
				}
			};
			for (int i = 0; i < edufwContent.length; i++) {
				for (int j = 0; j < edufwContent[i].length; j++) {
					tm.setValueAt(edufwContent[i][j], i, j);
				}
			}
			tm.setColumnIdentifiers(edufwHeader);

			CTable ct = new CTable(m, tm);
			ct.setEnabled(false);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			ct.setDefaultRenderer(Object.class, r);
			tpp.getCenterPanel().setLayout(new BorderLayout());
			tpp.getCenterPanel().add(new JScrollPane(ct), BorderLayout.CENTER);

			add(tpp);
			add(accessoryp);
			add(localStatuesp);
		}
	}

	class InfoListPanel extends JPanel {
		private SubPanel edufwp = null;
		private JComboBox<String> listChooser = null;
		private JComboBox<String> deptChooser = null;
		private JComboBox<String> yearChooser = null;
		private JComboBox<String> gradeChooser = null;
		private JLabel deptlb = null;
		private JLabel yearlb = null;
		private JLabel gradelb = null;

		@SuppressWarnings("serial")
		public InfoListPanel() {
			setLayout(null);
			setBackground(Color.white);

			edufwp = new SubPanel("列表：", 740, 420);
			edufwp.setLocation(30, 30);
			listChooser = new JComboBox<String>();
			listChooser.setPreferredSize(new Dimension(120, 20));
			deptlb = new JLabel("院系：");
			deptlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
			deptlb.setForeground(Color.white);
			deptChooser = new JComboBox<String>();
			deptChooser.setPreferredSize(new Dimension(120, 20));
			yearlb = new JLabel("学年：");
			yearlb.setFont(new Font("微软雅黑", Font.BOLD, 14));
			yearlb.setForeground(Color.white);
			yearChooser = new JComboBox<String>();
			yearChooser.setPreferredSize(new Dimension(120, 20));
			gradelb = new JLabel("年级：");
			gradelb.setFont(new Font("微软雅黑", Font.BOLD, 14));
			gradelb.setForeground(Color.white);
			gradeChooser = new JComboBox<String>();
			gradeChooser.setPreferredSize(new Dimension(120, 20));

			listChooser.addItem("student_list");
			listChooser.addItem("teacher_list");
			listChooser.addItem("course_list");
			listChooser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb = (JComboBox<String>) e.getSource();
					if (cb.getSelectedItem().equals("course_list")) {
						yearlb.setVisible(true);
						gradelb.setVisible(true);
						yearChooser.setVisible(true);
						gradeChooser.setVisible(true);
					} else {
						yearlb.setVisible(false);
						gradelb.setVisible(false);
						yearChooser.setVisible(false);
						gradeChooser.setVisible(false);
					}
				}
			});

			edufwp.getTopPanel().add(listChooser);
			edufwp.getTopPanel().add(deptlb);
			edufwp.getTopPanel().add(deptChooser);
			edufwp.getTopPanel().add(yearlb);
			edufwp.getTopPanel().add(yearChooser);
			edufwp.getTopPanel().add(gradelb);
			edufwp.getTopPanel().add(gradeChooser);
			yearlb.setVisible(false);
			gradelb.setVisible(false);
			yearChooser.setVisible(false);
			gradeChooser.setVisible(false);

			edufwp.getCenterPanel().setLayout(new BorderLayout());
			DefaultTableModel dtm = new DefaultTableModel(40, 5);
			JTable infoTable = new JTable(dtm);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			infoTable.setDefaultRenderer(Object.class, r);

			edufwp.getCenterPanel().add(new JScrollPane(infoTable),
					BorderLayout.CENTER);
			add(edufwp);

		}
	}

	class CommonCoursePanel extends JPanel {
		private FunctionBar fbar = null;
		private FunctionBtn[] fBtn = new FunctionBtn[5];
		private SubPanel edufwp = null;

		@SuppressWarnings("serial")
		public CommonCoursePanel() {
			setLayout(null);
			setBackground(Color.white);
			fbar = new FunctionBar();
			fbar.setLocation(0, 0);
			fBtn[0] = new FunctionBtn("AddBtn");
			fBtn[1] = new FunctionBtn("ModifyBtn");
			fBtn[2] = new FunctionBtn("DelBtn");
			fBtn[3] = new FunctionBtn("ImportBtn");
			fBtn[4] = new FunctionBtn("ExportBtn");

			for (int i = 0; i < fBtn.length; i++) {
				fbar.add(fBtn[i]);
			}
			add(fbar);

			edufwp = new SubPanel("通识课列表", 740, 380);
			edufwp.setLocation(30, 70);

			DefaultTableModel dtm = new DefaultTableModel(40, 5);
			JTable infoTable = new JTable(dtm);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			infoTable.setDefaultRenderer(Object.class, r);

			edufwp.getCenterPanel().setLayout(new BorderLayout());
			edufwp.getCenterPanel().add(new JScrollPane(infoTable),
					BorderLayout.CENTER);
			add(edufwp);

		}
	}

	class PECoursePanel extends JPanel {
		public PECoursePanel() {
			setBackground(Color.pink);
		}
	}
}
