package NJU.HouseWang.nju_eas_client.ui.CommonUI.Table;

public interface CMap {
	/**
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return 一个单元所包含的行数
	 */
	int span(int row, int column);

	/**
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return 合并得到的单元格的index
	 */
	int visibleCell(int row, int column);
}