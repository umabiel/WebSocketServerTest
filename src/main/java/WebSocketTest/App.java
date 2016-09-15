package WebSocketTest;

import WebSocketServer.WebSocketServer;
import org.glassfish.tyrus.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        startServer();
    }

    public static void startServer() {
        Server server = new Server("localhost", 8025, "/", WebSocketServer.class);
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("presione una tecla para apagar server");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }

    }
}
