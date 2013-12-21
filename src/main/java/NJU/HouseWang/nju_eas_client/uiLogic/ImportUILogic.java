package NJU.HouseWang.nju_eas_client.uiLogic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import NJU.HouseWang.nju_eas_client.net.ClientPool;
import NJU.HouseWang.nju_eas_client.netService.NetService;
import NJU.HouseWang.nju_eas_client.vo.Feedback;

public class ImportUILogic {

	private ClientPool cPool = null;
	
	private NetService initNetService() {
		cPool = ClientPool.getInstance();
		return cPool.getClient();
	}
	
	public ArrayList<String> readXls(String path) throws IOException {
		ArrayList<String> xls = new ArrayList<String>();
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

		for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			String s = new String();
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}

			for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
				HSSFCell hssfCell = hssfRow.getCell(cellNum);
				if (hssfCell == null) {
					continue;
				}

				s += getValue(hssfCell) + "；";
				System.out.print("\t | \t" + getValue(hssfCell));
			}
			xls.add(s);
			System.out.println();
		}
		return xls;
	}

	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf((long) hssfCell.getNumericCellValue());
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			return "null";
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public Feedback addItem(String itemName, String itemInfo) {
		String command = "add；" + itemName + "；" + itemInfo;
		String line = null;
		try {
			NetService client = initNetService();
			client.sendCommand(command);
			line = client.receiveFeedback();
			client.shutDownConnection();
			return Feedback.valueOf(line);
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.INTERNET_ERROR;
		}
	}
}
