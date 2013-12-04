package NJU.HouseWang.nju_eas_client.net;

import java.util.Stack;

public class ClientPool {
	private static ClientPool cPool = null;
	private Stack<Client> clientStack = new Stack<Client>();
	private Thread checkLinkNum = null;

	private static final int DEFAULT_MAX_SIZE = 32;
	private static final int DEFAULT_MIN_SIZE = 16;
	private static final String DEFAULT_IP = "localhost";
	private static final int DEFAULT_PORT = 9001;

	private int maxSize;
	private int minSize;
	private int linkNum = 0;
	private String ip;
	private int port;

	private ClientPool() {
		maxSize = DEFAULT_MAX_SIZE;
		minSize = DEFAULT_MIN_SIZE;
		ip = DEFAULT_IP;
		port = DEFAULT_PORT;
	}

	public static ClientPool getInstance() {

		if (cPool != null) {
			return cPool;
		} else {
			cPool = new ClientPool();
			return cPool;
		}
	}

	private ClientPool(String ip, int port, int maxSize, int minSize) {
		this.maxSize = maxSize;
		this.minSize = minSize;
		this.ip = ip;
		this.port = port;
	}

	public static ClientPool getInstance(String ip, int port, int max, int min) {
		if (cPool != null) {
			return cPool;
		} else {
			cPool = new ClientPool(ip, port, max, min);
			return cPool;
		}
	}

	// 初始化连接数
	private void initialize() throws Exception {
		for (int i = 0; i < maxSize; i++) {
			Client c = new Client(ip, port);
			c.createConnection();
			clientStack.push(c);
			linkNum++;
		}
	}

	public void shutdown() {
		checkLinkNum.interrupt();
		for (int i = linkNum; i > 0; i--) {
			clientStack.pop().shutDownConnection();
		}
	}

	public void run() throws Exception {
		initialize();
		checkLinkNum = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (linkNum < minSize) {
						try {
							initialize();
							Thread.sleep(500);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		checkLinkNum.start();
	}

	public Client getClient() {
		linkNum--;
		return clientStack.pop();
	}

}
