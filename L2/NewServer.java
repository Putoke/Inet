import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.System;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class NewServer{
    public  static void main(String[] args) throws java.io.IOException{
        System.out.println("Skapar Serversocket");
        ServerSocket ss = new ServerSocket(8080);
        while(true) {
            System.out.println("Väntar på klient...");
            Socket s = ss.accept();
            System.out.println("Klient är ansluten");
            BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = request.readLine();
            StringTokenizer tokens = new StringTokenizer(str, "? ");
            tokens.nextToken(); // Ordet GET
            String requestedDocument = tokens.nextToken();
            while( (str = request.readLine()) != null && str.length() > 0) {
                System.out.println(str);
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

            response.println("Set-Cookie: clientId=1; expires=Wednesday, 31-Dec-15 21:00:00 GMT");

            response.println();
            File f = new File("." + requestedDocument);
            FileInputStream infil = new FileInputStream(f);
            byte[]b = new byte[1024];
            while( infil.available() > 0 ){
                response.write(b, 0, infil.read(b));
            }
            s.shutdownOutput();
            s.close();
        }
    }
}