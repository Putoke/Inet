/**
 * Created by Fredrik on 1/25/2015.
 */
import java.io.*;
import java.net.*;

public class ServerThread extends Thread  {
    
    private Socket socket = null;

    public ServerThread (Socket socket) {
        super ("ServerThread");
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        ) {
            out.println("Welcome to fiskchat!");
            String output;
            while ((output = in.readLine()) != null) {
                Server.broadcast(socket, output);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}
