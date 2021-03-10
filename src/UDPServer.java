import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread {
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(50001);
            byte[] buffer = new byte[512];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            for (int count = 0; count < 100000; count++) {
                String data = "Message from server " + count;
                buffer = data.getBytes();
                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(response);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
