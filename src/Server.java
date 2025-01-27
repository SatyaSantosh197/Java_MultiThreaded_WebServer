// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.net.*;

public final class Server {
    public static void main(String[] args) throws Exception {
        // Set the port number.
        int port = 6789;

        // Establish the listen socket.
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Web server is running on port http://localhost:" + port);

        // Process HTTP service requests in an infinite loop.
        while (true) {
            // Listen for a TCP connection request.
            Socket clientSocket = serverSocket.accept();

            // Construct an object to process the HTTP request message.
            ClinetHandler request = new ClinetHandler(clientSocket);

            // Create a new thread to process the request.
            Thread thread = new Thread(request);

            // Start the thread.
            thread.start();
        }
    }
}
