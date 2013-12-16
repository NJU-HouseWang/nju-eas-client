package NJU.HouseWang.nju_eas_client.vo;

public enum Feedback {
	OPERATION_SUCCEED("操作成功"), 
	OPERATION_FAIL("操作失败"), 
	DATALIST_NOT_FOUND("数据列表不存在"), 
	LISTNAME_EMPTY("未设置列表名"), 
	DATA_NOT_FOUND("数据不存在"), 
	DATA_ALREADY_EXISTED("数据已存在"),
	IP_ALREADY_EXISTED("该IP已登录"),
	ID_ALREADY_EXISTED("该账号已在别地登录"),
	ID_PW_NOT_FOUND("登录信息错误"),
	USERTYPE_EMPTY("请选择用户类型"),
	USERNAME_EMPTY("请输入用户名"),
	PASSWORD_EMPTY("请输入密码"),
	INTERNET_ERROR("网络连接错误"),
	SELECTION_ERROR("您未选择列表中的项"),
	ITEM_EMPTY("项目不能为空"),
	TEACHINGPLAN_NOT_COMMIT("该院系的教学计划未提交");
	
	private String content;

	private Feedback(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
