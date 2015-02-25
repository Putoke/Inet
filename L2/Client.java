import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * Created by Fredrik on 2/1/2015.
 */
public class Client {

    public float averageGuess;

    public  Client() {

    }

    private String getConnectionResponse(HttpURLConnection con) throws IOException {
        BufferedReader infil = null;
        infil = new BufferedReader(new InputStreamReader(con.getInputStream()));

        StringBuilder sb = new StringBuilder();

        String rad = null;
        while ((rad = infil.readLine()) != null) {
            sb.append(rad);
        }

        infil.close();
        con.disconnect();
        return sb.toString();

    }


        //System.out.println(con.getHeaderField("Content-Type"));

    public void guess() throws IOException {
        URL url = new URL("http", "localhost", 8080, "/index.html");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestProperty("User-Agent", "Mozilla");
        //con.setRequestProperty("Cookie", "Set-Cookie: clientId=1; expires=Wednesday, 31-Dec-15 21:00:00 GMT");

        //con.connect();

        getConnectionResponse(con);

        // get client ID
        String cookie = con.getHeaderField(3);
        StringTokenizer st = new StringTokenizer(cookie, ";");
        cookie = st.nextToken();

        // GUESSINGK


        int low = 0;
        int high = 100;
        int noGuess = 0;

        while (true) {
            noGuess++;
            int guess = (high - low) / 2 + low;


            url = new URL("http", "localhost", 8080, "/index.html?numberguess=" + guess);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Cookie", cookie);
            String response = getConnectionResponse(con);

            if (response.contains("Too high")) {
                high = guess - 1;

            } else if (response.contains("Too low")) {
                low = guess + 1;
            } else if (response.contains("Yay")) {
                averageGuess += noGuess;
                break;
            }

        }





}


        public static void main(String[] args){
            try {
                Client client = new Client();
                for (int i = 0; i < 100; ++i) {
                    client.guess();
                }
                System.out.println("Aevgprutt = " + client.averageGuess/100);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
