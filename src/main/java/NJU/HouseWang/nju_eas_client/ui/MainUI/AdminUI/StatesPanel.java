package NJU.HouseWang.nju_eas_client.ui.MainUI.AdminUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import NJU.HouseWang.nju_eas_client.uiLogic.AdminUILogic;
import NJU.HouseWang.nju_eas_client.vo.Feedback;
import NJU.HouseWang.nju_eas_client.vo.TermVO;

@SuppressWarnings("serial")
public class StatesPanel extends JPanel {
	private AdminUILogic logic = new AdminUILogic();
	private TermVO curTerm = new TermVO();
	private JTextField textField;
	private JTextField textField_1;
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p5 = new JPanel();
	private JLabel currentTermlb = new JLabel("正在获取...");
	private JButton createNewBtn = new JButton("创建新学期");
	private JButton createBtn = new JButton("创建");
	private JButton cancelBtn = new JButton("取消");
	private JToggleButton chooseBtn = new JToggleButton(".");
	private JToggleButton byElectBtn = new JToggleButton(".");
	private JToggleButton quitBtn = new JToggleButton(".");
	private JLabel generateYear = new JLabel("????");

	public StatesPanel() {
		setLayout(null);

		p1.setBounds(200, 128, 400, 45);
		add(p1);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label = new JLabel("当前学期：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p1.add(label);

		p1.add(currentTermlb);
		createNewBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		createNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p1.setVisible(false);
				p5.setVisible(true);
			}
		});
		p1.add(createNewBtn);

		p2.setBounds(200, 183, 400, 45);
		add(p2);

		JLabel label_2 = new JLabel("通识课选课开关：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p2.add(label_2);

		chooseBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		chooseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean newStates = false;
				if (chooseBtn.getText().equals("关")) {
					newStates = true;
				}
				Feedback fb = logic.swicthStates("selectCommon",
						newStates.toString());
				System.out.println(chooseBtn.isSelected());
				if (fb.equals(Feedback.OPERATION_SUCCEED)
						&& newStates.equals(false)) {
					fb = logic.processSelection();
				}
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					if (newStates) {
						chooseBtn.setText("开");
					} else {
						chooseBtn.setText("关");
					}
				}
			}
		});
		p2.add(chooseBtn);

		p3.setBounds(200, 238, 400, 45);
		add(p3);

		JLabel label_3 = new JLabel("通识课补选开关：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p3.add(label_3);

		byElectBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		byElectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean newStates = false;
				if (byElectBtn.getText().equals("关")) {
					newStates = true;
				}
				Feedback fb = logic.swicthStates("byelectCommon",
						newStates.toString());
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					if (newStates) {
						byElectBtn.setText("开");
					} else {
						byElectBtn.setText("关");
					}
				}
			}
		});
		p3.add(byElectBtn);

		p4.setBounds(200, 293, 400, 45);
		add(p4);

		JLabel label_4 = new JLabel("通识课退选开关：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p4.add(label_4);

		quitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean newStates = false;
				if (quitBtn.getText().equals("关")) {
					newStates = true;
				}
				Feedback fb = logic.swicthStates("byelectCommon",
						newStates.toString());
				if (!fb.equals(Feedback.OPERATION_SUCCEED)) {
					JOptionPane.showMessageDialog(null, fb.getContent());
				} else {
					if (newStates) {
						quitBtn.setText("开");
					} else {
						quitBtn.setText("关");
					}
				}
			}
		});
		p4.add(quitBtn);

		p5.setBounds(125, 128, 550, 45);
		add(p5);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label_5 = new JLabel("创建学期：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p5.add(label_5);

		textField = new JTextField();
		p5.add(textField);
		textField.setColumns(4);

		JLabel label_6 = new JLabel("-");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p5.add(label_6);

		p5.add(generateYear);

		JLabel label_8 = new JLabel("学年");
		label_8.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p5.add(label_8);

		JLabel label_9 = new JLabel("第");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p5.add(label_9);

		textField_1 = new JTextField();
		p5.add(textField_1);
		textField_1.setColumns(1);

		JLabel label_10 = new JLabel("学期");
		label_10.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		p5.add(label_10);

		currentTermlb.setFont(new Font("微软雅黑", Font.PLAIN, 12));

		textField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				try {
					int i = Integer.parseInt(textField.getText());
					generateYear.setText((i + 1) + "");
				} catch (Exception e) {
					textField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textField.selectAll();
			}
		});
		createBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year1 = textField.getText();
				String termth = textField_1.getText();
				if (year1.equals("") || termth.equals("")) {
					JOptionPane.showMessageDialog(null, "数据不完整");
				} else {
					TermVO newTerm = new TermVO();
					newTerm.firstYear = year1;
					newTerm.secondYear = (Integer.parseInt(year1) + 1) + "";
					newTerm.termth = termth;
					logic.createNewTerm(newTerm.toString());
				}
				showTerm();
			}
		});
		p5.add(createBtn);
		cancelBtn.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(true);
				p5.setVisible(false);
			}
		});
		p5.add(cancelBtn);

		p5.setVisible(false);
		showTerm();
		showStates();
		validate();
	}

	public void showTerm() {
		Object o = logic.showCurrentTerm();
		if (o != null) {
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof TermVO) {
				curTerm = (TermVO) o;
			}
		}
		currentTermlb.setText(curTerm.toString());
	}

	public void showStates() {
		Object o = logic.showStates("selectCommon");
		if (o != null) {
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof Boolean) {
				chooseBtn.setSelected(((Boolean) o).booleanValue());
				if (((Boolean) o).booleanValue()) {
					chooseBtn.setText("开");
				} else {
					chooseBtn.setText("关");
				}
			}
		}

		o = logic.showStates("byelectCommon");
		if (o != null) {
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof Boolean) {
				byElectBtn.setSelected(((Boolean) o).booleanValue());
			}
			if (((Boolean) o).booleanValue()) {
				byElectBtn.setText("开");
			} else {
				byElectBtn.setText("关");
			}
		}

		o = logic.showStates("quitCommon");
		if (o != null) {
			if (o instanceof Feedback) {
				JOptionPane
						.showMessageDialog(null, ((Feedback) o).getContent());
			} else if (o instanceof Boolean) {
				quitBtn.setSelected(((Boolean) o).booleanValue());
			}
			if (((Boolean) o).booleanValue()) {
				quitBtn.setText("开");
			} else {
				quitBtn.setText("关");
			}
		}
	}
}
