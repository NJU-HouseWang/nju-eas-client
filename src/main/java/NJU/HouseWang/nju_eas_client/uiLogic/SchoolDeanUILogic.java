package NJU.HouseWang.nju_eas_client.uiLogic;

import NJU.HouseWang.nju_eas_client.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_client.uiLogicService.DelItemService;
import NJU.HouseWang.nju_eas_client.uiLogicService.DelListService;
import NJU.HouseWang.nju_eas_client.uiLogicService.EditItemService;
import NJU.HouseWang.nju_eas_client.uiLogicService.ShowTableService;

public class SchoolDeanUILogic implements DelListService, EditItemService,
		ShowTableService, DelItemService {

	@Override
	public Object showTableHead(String listName) {

		return null;
	}

	@Override
	public Object showTableContent(String listName) {

		return null;
	}

	@Override
	public Feedback delItem(String itemName, String itemInfo) {

		return null;
	}

	@Override
	public Feedback delList(String listName, String[][] table) {

		return null;
	}

}
