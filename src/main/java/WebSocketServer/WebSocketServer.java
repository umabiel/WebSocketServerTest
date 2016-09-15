package WebSocketServer;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by guxor on 9/15/16.
 */
@ServerEndpoint(value="/ws")
public class WebSocketServer {

    private static Queue<Session> wsListaSessiones = new ConcurrentLinkedQueue<Session>();

    @OnMessage
    public void message(Session session, String msg) {
        for (Session ses: wsListaSessiones) {
            ses.getAsyncRemote().sendText(msg);
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        wsListaSessiones.add(session);
        System.out.println("Se conecto un cliente.");
        this.message(session, new StringBuilder().append("<br><strong>Server</strong>> Bienvenido, cantidad de usuarios conectados ").append(wsListaSessiones.size()).append("").toString());
        //this.message(session, new StringBuffer().append("{ \"session\": \"").append(session.getId()).append("\"}").toString());
    }

    @OnClose
    public void closeConnection(Session session) {
        wsListaSessiones.remove(session);
        System.out.println("Se desconecto el cliente.");
    }

    @OnError
    public void onError(Session session, Throwable thrw) {
        wsListaSessiones.remove(session);
        System.out.printf("Error: " + thrw.getMessage());
    }

}
