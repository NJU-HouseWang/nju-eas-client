package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogicService.DelItemService;
import NJU.HouseWang.nju_eas_client.uiLogicService.DelListService;
import NJU.HouseWang.nju_eas_client.uiLogicService.ShowTableService;

public class DeptADUILogic implements DelListService, DelItemService,
		ShowTableService {

	@Override
	public Object showTableHead(String listName) {

		return null;
	}

	@Override
	public Object showTableContent(String listName) {

		return null;
	}

	@Override
	public Feedback delItem(String itemName, String id) {

		return null;
	}

	@Override
	public Feedback delList(String listName, String[][] table) {

		return null;
	}

}
