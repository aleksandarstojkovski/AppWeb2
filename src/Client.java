import java.io.*;
import java.net.Socket;

public class Client extends Thread{

    String exampleRequest = "GET /home.html HTTP/1.1\r\n"
            + "Host: www.supsi.ch\r\n"
            + "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:86.0) Gecko/20100101 Firefox/86.0\r\n"
            + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,/*;q=0.8\r\n"
            + "Accept-Language: en-GB,en;q=0.5\r\n"
            + "\r\n";

    public void run(){
        try {
            Socket client = new Socket("www.supsi.ch", 80); //Il programma client si connette al server
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter (new OutputStreamWriter(client.getOutputStream()));
            BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));
            //input da console
            while ( true ) {
                String line = lineReader.readLine();
                if ( line.equals("!q") ){
                    break;
                } else if (line.equals("example")){
                    out.println(exampleRequest); //scrivo sulla connessione
                } else {
                    out.println(line); //scrivo sulla connessione
                }
                out.flush(); //rliascia le risorse
                in.lines().forEach(System.out::println);
            }
            in.close();
            out.close();
            client.close();
        }
        catch (IOException ioe) {

        }
    }
}
