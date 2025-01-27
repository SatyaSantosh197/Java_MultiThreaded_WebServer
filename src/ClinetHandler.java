import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

final class ClinetHandler implements Runnable {
    final static String CRLF = "\r\n";
    Socket socket;

    // Constructor
    public ClinetHandler(Socket socket) throws Exception {
        this.socket = socket;
    }

    // Implement the run() method of the Runnable interface.
    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
        }
    }

    private void processRequest() throws Exception {

        // Get a reference to the socket's input and output streams.
        InputStream inputStream = socket.getInputStream();
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        // Set up input stream filters.
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // Get the request line of the HTTP request message.
        String requestLine = bufferedReader.readLine();

        // Display the request line.
        System.out.println();
        System.out.println(requestLine);

        // Get and display the header lines.
        String headerLine = null;
        while (!( headerLine = bufferedReader.readLine()).isEmpty()) {
            System.out.println(headerLine);
        }

        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();
        String fileName = tokens.nextToken();

        if(fileName.equals("/")) {
            fileName = "/index.html";
        }

        fileName = "./src" + fileName;

        System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());

        FileInputStream fileInputStream = null;
        boolean fileExists = true;
        try {
            fileInputStream = new FileInputStream(fileName);
            System.out.println("FileInputStream opened successfully for: " + fileName);
        } catch(FileNotFoundException e) {
            System.out.println("FileInputStream error: " + e.getMessage());
            fileExists = false;
        }


        // response message contains '3' parts: status line, response headers, entity body
        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;

        if(fileExists) {
            statusLine = "HTTP/1.0 200 OK" + CRLF;
            contentTypeLine = "content-type: " + contentType(fileName) + CRLF;
        } else {
            statusLine = "HTTP/1.0 404 NOT FOUND" + CRLF;
            contentTypeLine = "Content-type: text/html" + CRLF;
            entityBody = "<HTML>" +
                    "<HEAD><TITLE>Not Found</TITLE></HEAD>" +
                    "<BODY>Not Found</BODY></HTML>";
        }

        outputStream.writeBytes(statusLine);
        outputStream.writeBytes(contentTypeLine);
        outputStream.writeBytes(CRLF);

        if(fileExists) {

            sendBytes(fileInputStream, outputStream);
            fileInputStream.close();
        } else {
            outputStream.writeBytes(entityBody);
        }

        outputStream.close();
        bufferedReader.close();
        socket.close();
    }

    private static void sendBytes(FileInputStream fileInputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytes = 0;

        while((bytes = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytes);
        }
    }

    private static String contentType(String fileName) {
        if(fileName.endsWith(".html") || fileName.endsWith("./htm")) {
            return "text/html";
        }
        if(fileName.endsWith(".gif")) {
            return "image/gif";
        }
        if(fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
            return "image/jpeg";
        }
        return "application/octet-stream";
    }

}
