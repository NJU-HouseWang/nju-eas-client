package NJU.HouseWang.nju_eas_client.netService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 网络服务接口
 * 
 * @author Xin
 * @version 2013-11-2
 */
public interface NetService {
	/**
	 * 发送命令
	 * 
	 * @param cmd
	 *            命令
	 * @throws Exception
	 */
	public void sendCommand(String cmd) throws IOException;

	/**
	 * 发送文件
	 * 
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	public void sendFile(File file) throws IOException;

	/**
	 * 接收反馈
	 * 
	 * @return 接收的反馈
	 * @throws IOException
	 */
	public String receiveFeedback() throws IOException;

	/**
	 * 接收文件
	 * 
	 * @return 接收的文件
	 * @throws Exception
	 */
	public File receiveFile() throws Exception;

	/**
	 * 发送列表
	 * 
	 * @param list
	 *            发送的列表
	 * @throws IOException
	 */
	public void sendList(ArrayList<String> list) throws IOException;;

	/**
	 * 接收列表
	 * 
	 * @return 接收到的列表
	 * @throws Exception
	 */
	public ArrayList<String> receiveList() throws IOException;

	/**
	 * 创建连接
	 * 
	 * @throws Exception
	 */
	public void createConnection() throws IOException;

	/**
	 * 关闭连接
	 * 
	 * @throws Exception
	 */
	public void shutDownConnection() throws IOException;

}
