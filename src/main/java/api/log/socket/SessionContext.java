package api.log.socket;

import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class SessionContext {

    private WebSocketSession session;
    private String userId;
    private boolean connected;

    public SessionContext(WebSocketSession session) {
        this.session = session;
        this.connected = false;
        this.userId = session.getId();
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private String extractUserId(String query) {
        return query.split("=")[1];
    }
}