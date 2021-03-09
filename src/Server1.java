import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 extends Thread {

    String exampleReply = "HTTP/1.1 302 Found\n" +
            "Date: Wed, 03 Mar 2021 18:48:30 GMT\n" +
            "Server: Apache/2\n" +
            "Location: https://www.supsi.ch/home.html\n" +
            "Content-Length: 214\n" +
            "Content-Type: text/html; charset=iso-8859-1\n" +
            "\n" +
            "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
            "<html><head>\n" +
            "<title>302 Found</title>\n" +
            "</head><body>\n" +
            "<h1>Found</h1>\n" +
            "<p>The document has moved <a href=\"https://www.supsi.ch/home.html\">here</a>.</p>\n" +
            "</body></html>";

    public void run() {
        ServerSocket server;
        try {
            server = new ServerSocket(5000); // Il server si mette in ascolto
            while (true) //sulla porta di servizio
                try {
                    System.out.println("In attesa di connessione...");
                    Socket client = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    while (true) {
                        String s = in.readLine();
                        if (s.length() < 2) break;
                        else {
                            System.out.println(client + ". Messaggio: " + s);
                            StringBuffer reverse = new StringBuffer(s).reverse();
                            out.println(reverse);
                            // out.println(exampleReply);
                            out.flush(); //Rilsacia risolrse attiva le operazioni di scrittura
                        }
                    }
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException ioe) {

                }
        } catch (IOException ioe) {

        }
    }
}