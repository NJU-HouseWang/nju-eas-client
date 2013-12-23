package NJU.HouseWang.nju_eas_client.ui.MainUI.EditUserUI;

public class EditItemUIFactory {
	public static void showEditItemUI(String itemName, String[] old) {
		switch (itemName) {
		case "teacher；DeptAD":
		case "teacher；SchoolDean":
		case "teacher；Teacher":
		case "student":
		case "user":
			new EditUserUI(itemName, old);
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
