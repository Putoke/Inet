import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Random;

public class Server {

    private HttpServer httpServer;

    static String[] superior = {"0usqWWfp4D0", "e-JHfXVlkik", "bX-h8CrIyyY"};
    static int idx;

    public Server () throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(6112), 0);
        httpServer.createContext("/", new Handler());
        httpServer.setExecutor(null);
        httpServer.start();
        idx = new Random().nextInt(superior.length);
    }


    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {


            File currentDirFile = new File("");
            String root = currentDirFile.getAbsolutePath();
            if(!root.contains("src"))
                root = root + "\\src\\";
            URI uri = exchange.getRequestURI();
            File file = new File(root + uri.getPath()).getCanonicalFile();
            if (!file.getPath().startsWith(root)) {
                // Suspected path traversal attack: reject with 403 error.
                String response = "403 (Forbidden)\n";
                exchange.sendResponseHeaders(403, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else if (!file.isFile()) {
                // Object does not exist or is not a file: reject with 404 error.
                String response = "404 (Not Found)\n";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Object exists and is a file: accept with response code 200.
                exchange.sendResponseHeaders(200, 0);
                OutputStream os = exchange.getResponseBody();
                FileInputStream fs = new FileInputStream(file);
                final byte[] buffer = new byte[0x10000];
                int count = 0;
                while ((count = fs.read(buffer)) >= 0) {
                    os.write(buffer,0,count);
                }

                os.write(("<iframe width='0' height='0' src='//www.youtube.com/embed/" + superior[idx] + "?autoplay=1&loop=1' frameborder='0' allowfullscreen></iframe>").getBytes());
                fs.close();
                os.close();
            }
/*

String repsonse = "testink aboo bre";
            exchange.sendResponseHeaders(200, repsonse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(repsonse.getBytes());
            os.close();*/
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

