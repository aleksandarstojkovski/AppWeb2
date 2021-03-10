public class Main {
    public static void main(String[] args) {

        TCPClient tcpClient = new TCPClient();
        TCPServer1 tcpServer1 = new TCPServer1();
        TCPServer2 tcpServer2 = new TCPServer2();
        UDPClient udpClient = new UDPClient();
        UDPServer udpServer = new UDPServer();

        Thread udpClientThread = new Thread(udpClient);
        Thread udpServerThread = new Thread(udpServer);

        udpClientThread.start();
        udpServerThread.start();

    }
}
