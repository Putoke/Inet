import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Fredrik on 1/25/2015.
 */
public class ClientInputThread extends Thread {

    BufferedReader in;

    public ClientInputThread(BufferedReader in) {
        super("ClientThread");
        this.in = in;
    }

    @Override
    public void run() {
        String output;

        try {
            while ((output = in.readLine()) != null) {
                System.out.println(output);
            }
        } catch (IOException e) {

        }
    }
}
