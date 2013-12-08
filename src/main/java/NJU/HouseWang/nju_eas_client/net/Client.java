/*
 * 文件名：Client.java
 * 创建者：王鑫
 * 创建时间：2013-10-09
 * 最后修改：王鑫
 * 最后修改时间：2013-11-2
 */
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

/*
 * 类：Client
 * 功能：客户端与服务器的连接，网络命令的发送，网络文件传输
 */
public class Client implements NetService {
	private static final String DEFAULT_IP = "localhost";
	private static final int DEFAULT_PORT = 9001;
	private String ip;
	private int port;

	private Socket socket = null;

	private DataInputStream in = null;
	private DataOutputStream out = null;
	private DataInputStream fis = null;
	private DataOutputStream fos = null;

	public Client() {
		ip = DEFAULT_IP;
		port = DEFAULT_PORT;
	}

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void createConnection() throws Exception {
		try {
			socket = new Socket(ip, port);
			System.out.println("Create Connection to ip" + ip + ":" + port);
		} catch (Exception e) {
			e.printStackTrace();
			if (socket != null) {
				socket = null;
			}
			throw e;
		}
	}

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

	public void sendCommand(String command) throws Exception {
		System.out.println("Send Command:" + command);
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(command);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

	public String receiveFeedback() throws IOException {
		in = new DataInputStream(new BufferedInputStream(
				socket.getInputStream()));
		String feedback = new String();
		feedback = in.readUTF();
		System.out.println("Receive Feedback:" + feedback);
		return feedback;
	}

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
			e.printStackTrace();
			if (out != null) {
				out.close();
			}
			throw e;
		}
	}

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

	public void sendFile(File file) throws Exception {
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
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				fis.close();
				out.close();
			}
			throw e;
		}
	}

	public File receiveFile() throws Exception {
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
		} catch (Exception e) {
			System.out.println("接收消息错误" + "\n");
			throw new Exception();
		}
	}
}
