public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        Server socketServer = new Server();

        Thread clientThread = new Thread(client);
        Thread serverThread = new Thread(socketServer);

        clientThread.start();
        serverThread.start();

    }
}
