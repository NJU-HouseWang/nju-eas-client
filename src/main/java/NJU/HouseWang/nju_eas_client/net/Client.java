package NJU.HouseWang.nju_eas_client.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_client.netService.NetService;

/**
 * 客户端类
 * 
 * @author Xin
 * @version 2013-12-12
 */
public class Client implements NetService {
	/**
	 * 默认IP地址
	 */
	private static final String DEFAULT_IP = "localhost";
	/**
	 * 默认端口
	 */
	private static final int DEFAULT_PORT = 9001;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * 客户端套接字
	 */
	private Socket socket = null;
	/**
	 * 网络数据输入流
	 */
	private DataInputStream in = null;
	/**
	 * 网络数据输出流
	 */
	private DataOutputStream out = null;
	/**
	 * 文件数据输入流
	 */
	private DataInputStream fis = null;
	/**
	 * 文件数据输出流
	 */
	private DataOutputStream fos = null;

	/**
	 * 客户端构造方法
	 */
	public Client() {
		ip = DEFAULT_IP;
		port = DEFAULT_PORT;
	}

	/**
	 * 客户端构造方法
	 * 
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 */
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	/**
	 * 创建连接
	 * 
	 * @throws IOException
	 *             创建连接时遇到的错误
	 */
	public void createConnection() throws IOException {
		try {
			socket = new Socket(ip, port);
			System.out.println("Create Connection to ip" + ip + ":" + port);
		} catch (IOException e) {
			if (socket != null) {
				socket = null;
			}
			throw e;
		}
	}

	/**
	 * 关闭连接
	 */
	public void shutDownConnection() {
		System.out.println("Shutdown Connection");
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (fis != null)
				fis.close();
			if (out != null)
				out.close();
			if (socket != null)
				socket.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 发送命令
	 * 
	 * @param command
	 *            命令
	 * @throws IOException
	 *             发送命令时遇到的错误
	 */
	public void sendCommand(String command) throws IOException {
		System.out.println("Send Command:" + command);
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(command);
			out.flush();
		} catch (IOException e) {
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

	/**
	 * 接收反馈
	 * 
	 * @return 服务器反馈
	 * @throws IOException
	 *             接收反馈时遇到的错误
	 */
	public String receiveFeedback() throws IOException {
		String feedback = new String();
		try {
			in = new DataInputStream(new BufferedInputStream(
					socket.getInputStream()));
			feedback = in.readUTF();
		} catch (IOException e) {
			if (in != null) {
				in.close();
			}
			throw e;
		}
		System.out.println("Receive Feedback:" + feedback);
		return feedback;
	}

	/**
	 * 发送列表
	 * 
	 * @param list
	 *            要发送的列表
	 * @throws IOException
	 *             发送列表遇到的错误
	 */
	public void sendList(ArrayList<String> list) throws IOException {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("listStart");
			for (String str : list) {
				out.writeUTF(str);
				System.out.println("Send List Item :" + str);
			}
			out.writeUTF("listEnd");
			out.flush();
		} catch (Exception e) {
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

	/**
	 * 接收列表
	 * 
	 * @return 接收到的列表
	 * @throws IOException
	 *             接收列表时遇到的错误
	 */
	public ArrayList<String> receiveList() throws IOException {
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		ArrayList<String> list = new ArrayList<String>();
		String line;
		while (!in.readUTF().equals("listStart")) {
			System.out.println("Waiting for Server......");
		}
		while (!(line = in.readUTF()).trim().equals("listEnd")) {
			System.out.println("Receive List Item :" + line);
			list.add(line);
		}
		return list;
	}

	/**
	 * 发送文件
	 * 
	 * @param file
	 *            要发送的文件
	 * @throws IOException
	 *             发送文件时遇到的错误
	 */
	public void sendFile(File file) throws IOException {
		try {
			fis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(file.getName());
			out.flush();
			out.writeLong(file.length());
			out.flush();

			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];

			while (true) {
				int readState = 0;
				if (fis != null) {
					readState = fis.read(buf);
				}

				if (readState == -1) {
					break;
				}
				out.write(buf, 0, readState);
			}
			out.flush();
		} catch (IOException e) {
			if (out != null) {
				fis.close();
				out.close();
			}
			throw e;
		}
	}

	/**
	 * 接收文件
	 * 
	 * @return 接收到的文件
	 * @throws IOException
	 *             接收文件时遇到的错误
	 */
	public File receiveFile() throws IOException {
		try {
			in = new DataInputStream(new BufferedInputStream(
					socket.getInputStream()));
			// 本地保存路径，文件名会自动从服务器端继承而来。
			String savePath = "/download";
			int bufferSize = 8192;
			byte[] buf = new byte[bufferSize];
			int passedlen = 0;
			long len = 0;

			savePath += in.readUTF();
			fos = new DataOutputStream(new BufferedOutputStream(
					new BufferedOutputStream(new FileOutputStream(savePath))));
			len = in.readLong();

			System.out.println("文件的长度为:" + len + "\n");
			System.out.println("开始接收文件!" + "\n");

			while (true) {
				int read = 0;
				if (in != null) {
					read = in.read(buf);
				}
				passedlen += read;
				if (read == -1) {
					break;
				}
				// 下面进度条本为图形界面的prograssBar做的，这里如果是打文件，可能会重复打印出一些相同的百分比
				System.out.println("文件接收了" + (passedlen * 100 / len) + "%\n");
				fos.write(buf, 0, read);
			}
			System.out.println("接收完成，文件存为" + savePath + "\n");

			fos.close();
			return new File(savePath);
		} catch (IOException e) {
			System.out.println("接收消息错误" + "\n");
			throw e;
		}
	}
}
