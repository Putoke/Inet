import com.sun.net.ssl.internal.ssl.Provider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.net.ssl.*;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.*;

import java.security.cert.*;

public class Main {

    public static void main(String[] args) {

        int SSLport = 8080;

        // register new JSSE provider
        Security.addProvider(new Provider());

        // specify keystore details
        System.setProperty("javax.net.ssl.keyStore", "testkeystore.ks");
        System.setProperty("javax.net.ssl.keyStorePassword", "bananpaj");

        //System.setProperty("javax.net.debug","all");

        try {
            SSLServerSocketFactory sslssfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslssfact.createServerSocket(SSLport);
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

            System.out.println("client connected!");

            BufferedWriter outfile = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            outfile.write("Hello World");
            outfile.flush();
            outfile.close();
            sslSocket.close();
            sslServerSocket.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }





    }
}
