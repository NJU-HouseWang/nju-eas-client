package NJU.HouseWang.nju_eas_client.net;

import java.io.IOException;
import java.util.Stack;

/**
 * 网络连接池
 * 
 * @author Xin
 * @version 2013-12-12
 */
public class ClientPool {
	/**
	 * 连接池单件
	 */
	private static ClientPool cPool = null;
	/**
	 * 存放连接的栈
	 */
	private Stack<Client> clientStack = new Stack<Client>();

	/**
	 * 默认最大连接数
	 */
	private static final int DEFAULT_MAX_SIZE = 32;
	/**
	 * 默认最小连接数
	 */
	private static final int DEFAULT_MIN_SIZE = 16;
	/**
	 * 默认IP
	 */
	private static final String DEFAULT_IP = "localhost";
	/**
	 * 默认端口
	 */
	private static final int DEFAULT_PORT = 9100;

	/**
	 * 最大连接数
	 */
	private int maxSize;
	/**
	 * 最小连接数
	 */
	private int minSize;
	/**
	 * 当前连接数
	 */
	private int linkNum = 0;
	/**
	 * 当前IP
	 */
	private String ip;
	/**
	 * 当前端口
	 */
	private int port;

	/**
	 * 以默认配置构造
	 */
	private ClientPool() {
		maxSize = DEFAULT_MAX_SIZE;
		minSize = DEFAULT_MIN_SIZE;
		ip = DEFAULT_IP;
		port = DEFAULT_PORT;
	}

	/**
	 * 以默认配置取得单件
	 * 
	 * @return ClientPool单件
	 */
	public static ClientPool getInstance() {

		if (cPool != null) {
			return cPool;
		} else {
			cPool = new ClientPool();
			try {
				cPool.initialize();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cPool;
		}
	}

	/**
	 * 自定义构造
	 * 
	 * @param ip
	 * @param port
	 * @param maxSize
	 * @param minSize
	 */
	private ClientPool(String ip, int port, int maxSize, int minSize) {
		this.maxSize = maxSize;
		this.minSize = minSize;
		this.ip = ip;
		this.port = port;
	}

	/**
	 * 得到自定义构造的单件
	 * 
	 * @param ip
	 * @param port
	 * @param max
	 * @param min
	 * @return
	 */
	public static ClientPool getInstance(String ip, int port, int max, int min) {
		if (cPool != null) {
			return cPool;
		} else {
			cPool = new ClientPool(ip, port, max, min);
			return cPool;
		}
	}

	/**
	 * 初始化连接数
	 */
	private void initialize() {
		for (int i = 0; i < maxSize; i++) {
			Client c = new Client(ip, port);
			try {
				c.createConnection();
			} catch (IOException e) {
				break;
			}
			clientStack.push(c);
			linkNum++;
		}
	}

	/**
	 * 关闭连接池
	 */
	public void shutdown() {
		while (clientStack.isEmpty()) {
			clientStack.pop().shutDownConnection();
		}
	}

	/**
	 * 得到连接
	 * 
	 * @return 连接
	 */
	public Client getClient() {
		if (linkNum < minSize) {
			new Thread(new Runnable() {
				public void run() {
					initialize();
				}
			}).start();
		}
		linkNum--;
		return clientStack.pop();
	}

}
