/*
 * 文件名：FunctionBtn.java
 * 创建者：王鑫
 * 创建时间：2013-10-27
 * 最后修改：王鑫
 * 修改时间：2013-10-29
 */
package NJU.HouseWang.nju_eas_client.ui.CommonUI.FunctionBtn;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * 类：FunctionBtn
 * 
 */
public class FunctionBtn extends JButton {
	private String path = "img/FunctionIcon/";
	private String name = null;
	private String suffix = ".png";

	public FunctionBtn(String btnName) {
		name = btnName;
		setIcon(new ImageIcon(path + name + "_0" + suffix));
		setRolloverIcon(new ImageIcon(path + name + "_1" + suffix));
		setPressedIcon(new ImageIcon(path + name + "_2" + suffix));
		setSize(90, 45);
		setPreferredSize(new Dimension(90, 45));
		setBorderPainted(false);
		updateUI();
	}
}
