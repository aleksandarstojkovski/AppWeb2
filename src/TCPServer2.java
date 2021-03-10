import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer2 extends Thread {

    int requests = 1;

    String exampleReply = "HTTP/1.1 200 OK\n" +
            "Date: Wed, 03 Mar 2021 18:48:30 GMT\n" +
            "Server: Apache/2\n" +
            "Location: 127.0.0.1:5000\n" +
            "Content-Type: text/html; charset=iso-8859-1\n" +
            "Content-Length: 214\n" +
            "\n" +
            "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
            "<html><head>\n" +
            "<title>302 Found</title>\n" +
            "</head><body>\n" +
            "<h1>Test</h1>\n" +
            "<p>The document has moved <a href=\"https://www.supsi.ch/home.html\">here</a>.</p>\n" +
            "</body></html>";

    String headerWithoutContentLength = "HTTP/1.1 200 OK\n" +
            "Date: Wed, 03 Mar 2021 18:48:30 GMT\n" +
            "Server: Apache/2\n" +
            "Location: 127.0.0.1:5000\n" +
            "Content-Type: text/html; charset=iso-8859-1\n";

    public String getHeader(String body){
        byte[] b = new byte[0];
        try {
            b = body.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String contentLength="Content-Length: "+b.length+"\n";
        return headerWithoutContentLength+contentLength;
    }

    public String getReply(String message){
        String body = "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Esercizio Riuscito</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Esercizio Riuscito</h1>\n" +
                "<p>Chiamata numero: "+ requests +"</p>\n" +
                message +
                "</body>\n" +
                "</html>";
        return getHeader(body) + "\n" + body;
    }

    public void run() {
        ServerSocket server;
        try {
            server = new ServerSocket(5000); // Il server si mette in ascolto
            while (true) //sulla porta di servizio
                try {
                    System.out.println("In attesa di connessione...");
                    Socket client = server.accept();
                    String incomingRequest = "";
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                    while (true) {
                        String s = in.readLine();
                        if (s.length() < 2) break;
                        else {
                            incomingRequest += s + "<br>";
                        }
                    }
                    out.println(getReply(incomingRequest));
                    out.flush(); //Rilsacia risolrse attiva le operazioni di scrittura
                    in.close();
                    out.close();
                    client.close();
                    requests++;
                } catch (IOException ioe) {

                }
        } catch (IOException ioe) {

        }
    }
}
