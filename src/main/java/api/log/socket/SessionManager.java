package api.log.socket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
@Component("alogSessionManager")
public class SessionManager {

    private final Map<String, SessionContext> sessionMap = new ConcurrentHashMap<>();

    public void addSession(String sessionId, SessionContext context) {
        sessionMap.put(sessionId, context);
    }

    public void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    public SessionContext getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
