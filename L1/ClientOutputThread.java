import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Fredrik on 1/25/2015.
 */
public class ClientOutputThread extends Thread {

    PrintWriter out;

    public ClientOutputThread(PrintWriter out) {
        super("ClientThread");
        this.out = out;
    }

    @Override
    public void run() {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try  {
            while ((input = stdIn.readLine()) != null) {
                out.println(input);
            }

        } catch (IOException e) {

        }
    }
}