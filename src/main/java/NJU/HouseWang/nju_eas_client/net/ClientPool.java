package NJU.HouseWang.nju_eas_client.net;

import java.util.Stack;

public class ClientPool {
	private static ClientPool cPool = null;
	private final int poolSize = 100;
	private int linkNum = 0;
	private String ip = "localhost";
	private int port = 9001;
	private Stack<Client> clientStack = new Stack<Client>();
	private Thread checkLinkNum = null;

	// 初始化连接数
	private void initialize() throws Exception {
		for (int i = 0; i < poolSize; i++) {
			Client c = new Client(ip, port);
			c.createConnection();
			clientStack.push(c);
		}
		linkNum += poolSize;
	}

	public void shutdown() {
		checkLinkNum.interrupt();
		for (int i = linkNum; i > 0; i--) {
			clientStack.pop().shutDownConnection();
		}
	}

	public void replenish() throws Exception {
		for (int i = 0; i < poolSize / 2; i++) {
			Client c = new Client(ip, port);
			c.createConnection();
			c.sendCommand("...");
			clientStack.push(c);
		}
		linkNum += poolSize / 2;
	}

	public void run() throws Exception {
		initialize();
		checkLinkNum = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (linkNum < 10) {
						try {
							replenish();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
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

	private ClientPool() {
	}

	public static ClientPool getInstance() throws Exception {
		if (cPool != null) {
			return cPool;
		} else {
			ClientPool cPool = new ClientPool();
			cPool.run();
			return cPool;
		}
	}
}
