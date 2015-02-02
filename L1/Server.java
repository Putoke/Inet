import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
	private static ArrayList<Socket> sockets = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
        
		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				Socket s = serverSocket.accept();
				sockets.add(s);
				new ServerThread(s).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}

    public static synchronized void broadcast(Socket s, String message) {
        for (Socket socket : sockets) {
            if (!socket.equals(s)) {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("["  + s.getInetAddress() + ":" + s.getPort() + "] " + message);
                } catch (IOException e) {

                }

            }

        }
    }



}
