package NJU.HouseWang.nju_eas_client.uiLogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * xls文件创建器
 * 
 * @author 王鑫（参考自hao0610@iteye.com）
 * @version 2013-12-09
 */
public class ExportUILogic {

	/**
	 * 创建xls文件
	 * 
	 * @param str
	 *            要写入的文件内容
	 * @param path
	 *            文件路径
	 * @return File
	 * @throws IOException
	 */
	public File buildXls(String[][] str, String path) throws IOException {
		// 创建Excel的Workbook，对应到一个Excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet，对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet();
		// 行指针
		HSSFRow row = null;
		// 单元格指针
		HSSFCell cell = null;
		// 遍历行
		for (int i = 0; i < str.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < str[i].length; j++) {
				sheet.setColumnWidth(j, 4000);
				cell = row.createCell(j);
				cell.setCellValue(str[i][j]);
			}
		}
		// 写文件
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.flush();
		fos.close();
		return new File(path);
	}

	/**
	 * 创建xls文件（有表头）
	 * 
	 * @param head
	 *            表头
	 * @param str
	 *            列表
	 * @param path
	 *            文件路径
	 * @return File
	 * @throws IOException
	 */
	public File buildXls(String[] head, String[][] str, String path)
			throws IOException {
		// 创建Excel的Workbook，对应到一个Excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet，对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet();
		// 行指针
		HSSFRow row = null;
		// 单元格指针
		HSSFCell cell = null;

		// 创建表头
		row = sheet.createRow(0);
		// 表头样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 表头字体加粗
		HSSFFont font = wb.createFont();
		font.setFontHeight((short) 250);
		font.setBoldweight((short) 100);
		style.setFont(font);

		for (int i = 0; i < head.length; i++) {
			sheet.setColumnWidth(i, 4000);
			cell = row.createCell(i);
			cell.setCellValue(head[i]);
			cell.setCellStyle(style);
		}

		// 遍历行
		for (int i = 0; i < str.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < str[i].length; j++) {
				cell = row.createCell(j);
				cell.setCellValue(str[i][j]);
			}
		}
		// 写文件
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.flush();
		fos.close();
		return new File(path);
	}
}
