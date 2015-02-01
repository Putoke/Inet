import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.Integer;
import java.lang.System;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Random;

public class Server {

    private class Tuple {
        public int x, y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



    public ServerSocket ss = new ServerSocket(8080);
    public int current_guess = -1;
    public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public HashMap<Integer, Tuple> map2 = new HashMap<Integer, Tuple>();

    public Server() throws IOException {
        run();
    }

    public void run() throws IOException {

        System.out.println("Skapar Serversocket");
        int id_counter = 0;
        int current_id = -1;

        while(true) {
            System.out.println("Väntar på klient...");
            Socket s = ss.accept();
            current_id = -1;

            System.out.println("Klient är ansluten");
            BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = request.readLine();
            StringTokenizer tokens = new StringTokenizer(str, "? ");
            tokens.nextToken(); // Ordet GET
            String requestedDocument = tokens.nextToken();
            String guess = tokens.nextToken();
            if(guess.contains("numberguess=")) {
                guess = guess.replace("numberguess=", "");
                current_guess = Integer.parseInt(guess);
            }
            while( (str = request.readLine()) != null && str.length() > 0) {
                System.out.println(str);
                if(str.contains("Cookie: clientId=")) {
                    str = str.replace("Cookie: clientId=", "");
                    current_id = Integer.parseInt(str);
                    if(map.get(current_id) == null) {
                        map.put(current_id, new Random().nextInt(100)+1);
                        map2.put(current_id, new Tuple(-1,101));
                    }
                }
            }
            if(current_id == -1) {
                id_counter++;
                current_id = id_counter;
                map.put(current_id, new Random().nextInt(100)+1);
                map2.put(current_id, new Tuple(-1,101));
            }

            if(current_guess == -1337) {
                map.put(current_id, new Random().nextInt(100)+1);
                map2.put(current_id, new Tuple(-1,101));
                current_guess = -1;
            }

            System.out.println("Förfrågan klar.");
            s.shutdownInput();

            PrintStream response = new PrintStream(s.getOutputStream());
            response.println("HTTP/1.1 200 OK");
            response.println("Server : Slask 0.1 Beta");
            if(requestedDocument.indexOf(".html") != -1)
                response.println("COntent-Type: text/html");
            if(requestedDocument.indexOf(".gif") != -1)
                response.println("Content-Type: image/gif");

            response.println("Set-Cookie: clientId="+current_id+"; expires=Wednesday, 31-Dec-15 21:00:00 GMT");


            response.println();
            File f = new File("." + requestedDocument);
            FileInputStream infil = new FileInputStream(f);
            byte[]b = new byte[1024];
            while( infil.available() > 0 ){
                response.write(b, 0, infil.read(b));
            }

            if(current_guess != -1) {
                response.write("<br>".getBytes());
                int low = map2.get(current_id).x, high = map2.get(current_id).y;
                Tuple t = map2.get(current_id);
                if (current_guess == map.get(current_id)) {
                    response.write("Yay you guessed right!".getBytes());
                }
                if (current_guess > map.get(current_id)) {
                    t.y = high < current_guess ? high : current_guess;
                    if(t.x < 0 || t.y > 100)
                        response.write("Too high".getBytes());
                    else
                        response.write(("Too high, guess a number between " + t.x + " and " + t.y).getBytes());
                }
                if (current_guess < map.get(current_id)) {
                    t.x = low > current_guess ? low : current_guess;
                    if (t.x < 0 || t.y > 100)
                        response.write("Too low".getBytes());
                    else
                        response.write(("Too low, guess a number between " + t.x + " and " + t.y).getBytes());
                }
            }

            s.shutdownOutput();
            s.close();
        }

    }


    public  static void main(String[] args) throws IOException {
        new Server();
    }
}