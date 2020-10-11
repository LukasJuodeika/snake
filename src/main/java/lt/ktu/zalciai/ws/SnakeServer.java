package lt.ktu.zalciai.ws;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import lt.ktu.zalciai.ws.logging.LoggingServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class SnakeServer extends LoggingServer {

    private final ArrayList<WebSocket> clients = new ArrayList<>();

	public SnakeServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
	    super.onOpen(conn, handshake);
		clients.add(conn);
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
	    super.onClose(conn, code, reason, remote);
	    clients.remove(conn);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
	    super.onMessage(conn, message);
		for (WebSocket client : clients) {
			if (client != conn) {
				client.send(message);
			}
		}
	}

	public static void main(String[] args) {
		String host = "localhost";
		int port = 8887;

		WebSocketServer server = new SnakeServer(new InetSocketAddress(host, port));
		server.run();
	}
}