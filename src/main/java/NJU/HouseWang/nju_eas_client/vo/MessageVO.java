package NJU.HouseWang.nju_eas_client.vo;

public class MessageVO {
	// 消息编号
	public String id;
	// 发信人ID
	public String senderId;
	// 收信人ID
	public String recipientId;
	// 标题
	public String title;
	// 正文内容
	public String content;
	// 0表示未读,1表示已读
	public String status;

	public MessageVO() {
	}

	public MessageVO(String s) {
		id = s.split("；")[0];
		senderId = s.split("；")[1];
		recipientId = s.split("；")[2];
		title = s.split("；")[3];
		content = s.split("；")[4];
		status = s.split("；")[5];
	}

	public String toCommand() {
		return senderId + "；" + recipientId + "；" + title + "；" + content + "；"
				+ status;
	}
}
