package NJU.HouseWang.nju_eas_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServer {

	public void start() throws IOException {
		ServerSocket server = new ServerSocket(9001);

		while (true) {
			Socket socket = server.accept();
			System.out.println("#" + socket.getInetAddress() + "\t Connected!");
			invoke(socket);
		}
	}

	private void invoke(final Socket socket) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				MockSocketThread st = new MockSocketThread(socket);
				try {
					String get = st.receiveCommand();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
