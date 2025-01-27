# Java Multi-Threaded Web Server

This repository contains the implementation of a multi-threaded web server in Java. The project is based on the documentation provided by IIT Hyderabad's tutorial on building a multi-threaded web server. It demonstrates how to handle HTTP requests and responses using multi-threading, making it capable of serving multiple clients simultaneously.

---

## Features
- **HTTP/1.0 Compliant**: Supports basic HTTP operations.
- **Multi-Threading**: Handles multiple client connections in parallel using Java threads.
- **Static File Serving**: Serves static files like HTML pages.
- **Error Handling**: Returns appropriate HTTP error responses (e.g., 404 Not Found).
- **Extensible Design**: Can be extended to include additional features, such as dynamic content generation or security enhancements.

---

## How It Works
1. The server listens for client requests on a specified port.
2. Each incoming request is processed by a separate thread.
3. Static files (like HTML, images) are served from the current directory.
4. If a requested file is not found, the server responds with a 404 error message.

---

## Usage

### 1. Clone the Repository
```bash
git clone https://github.com/SatyaSantosh197/Java_MultiThreaded_WebServer.git
cd Java_MultiThreaded_WebServer
```

### 2. Compile the Source Code
```bash
javac -d bin src/*.java
```

### 3. Run the Server
```bash
java -cp bin Server
```

The server will start listening on a specified port (e.g., 3000).


## Directory Structure
```bash
Java_MultiThreaded_WebServer/
├── .gitignore                # Git ignore rules
├── .idea/                    # IntelliJ IDEA project settings
├── Java_Based_MultiThreaded_WebServer.iml  # IntelliJ project file
├── src/                      # Source files
│   ├── Server.java           # Main server implementation
│   ├── HttpRequest.java      # Handles individual HTTP requests
│   ├── ClinetHandler.java    # (Optional) Additional client handling logic
│   ├── index.html            # Example static HTML page
└── README.md                 # Project documentation
```

## Future Enhancements
  - [ ] Support for HTTP/1.1 features like persistent connections.
  - [ ] Dynamic content serving (e.g., database integration, API support).

## Acknowledgements
  - IIT Hyderabad: Tutorial on building a multi-threaded web server.
  - Java Documentation: Official Java networking and threading resources.
