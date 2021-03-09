public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        Server2 socketServer = new Server2();

        Thread clientThread = new Thread(client);
        Thread serverThread = new Thread(socketServer);

        //clientThread.start();
        serverThread.start();

    }
}
