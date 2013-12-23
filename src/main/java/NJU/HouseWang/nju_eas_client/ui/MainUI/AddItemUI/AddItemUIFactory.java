package NJU.HouseWang.nju_eas_client.ui.MainUI.AddItemUI;

public class AddItemUIFactory {
	public static void showAddItemUI(String itemName) {
		switch (itemName) {
		case "teacher；DeptAD":
		case "teacher；SchoolDean":
		case "teacher；Teacher":
		case "student":
			new AddUserUI(itemName);
			break;
		case "eduframework":
			break;
		case "teachingplan":
			break;
		case "course":
			break;
		}
	}
}
