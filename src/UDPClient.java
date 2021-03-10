import java.io.IOException;
import java.net.*;

public class UDPClient extends Thread {
    public void run() {
        try {
            String hostname = "localhost";
            int port = 50001;
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[512];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);
            int count = 0;
            int x = 0;
            while (true) {
                socket.receive(request);
                String message = new String(buffer, 0, request.getLength());
                System.out.println(count++ + " " + message);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
