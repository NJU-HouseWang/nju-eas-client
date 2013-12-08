/*
 * 文件名：Client.java
 * 创建者：王鑫
 * 创建时间：2013-10-09
 * 最后修改：王鑫
 * 最后修改时间：2013-11-2
 */
package NJU.HouseWang.nju_eas_client.netService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * 接口：NetService
 * 
 */
public interface NetService {
	// 发送指令
	public void sendCommand(String cmd) throws Exception;

	// 发送文件
	public void sendFile(File file) throws Exception;

	// 接收命令
	public String receiveFeedback() throws IOException;

	// 接收文件
	public File receiveFile() throws Exception;

	// 发送列表
	public void sendList(ArrayList<String> list) throws IOException;;

	// 接收列表
	public ArrayList<String> receiveList() throws Exception;

	public void createConnection() throws Exception;

	public void shutDownConnection() throws Exception;

}
