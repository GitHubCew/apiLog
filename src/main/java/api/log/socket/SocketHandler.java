package api.log.socket;

import api.log.base.Constant;
import api.log.cmd.ClearAll;
import api.log.cmd.Ls;
import api.log.cmd.Monitor;
import api.log.cmd.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
@Component("alogSocketHandler")
public class SocketHandler extends TextWebSocketHandler {

    @Qualifier("alogSessionManager")
    @Autowired
    private SessionManager sessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        SessionContext context = new SessionContext(session);
        sessionManager.addSession(session.getId(), context);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String cmd = message.getPayload();
        try {
           executeCommand(cmd, sessionManager.getSession(session.getId()));
        } catch (Exception e) {
            session.sendMessage(new TextMessage("Error: " + e.getMessage() + Constant.LINE_SEPARATOR));
        }
    }


    private void executeCommand(String cmd, SessionContext context) throws IOException {

        WebSocketSession session = context.getSession();
        String userId = context.getUserId();
        if (Objects.isNull(cmd) || cmd.isEmpty()) {
            sendToClient(session, "请输入命令");
        }
        else {
            String trimCmd = cmd.trim();
            Object result = "";
            if (trimCmd.startsWith("ls")) {
                Ls ls = new Ls(trimCmd);
                result = ls.exec(userId);
                sendToClient(session, result.toString().replace(Constant.SEPARATOR, Constant.LINE_SEPARATOR));
            }
            else if (trimCmd.startsWith("monitor")) {
                result = new Monitor(trimCmd).exec(userId);
                sendToClient(session, result.toString());
            } else if (trimCmd.startsWith("remove")) {
                result = new Remove(trimCmd).exec(userId);
                sendToClient(session, result.toString());
            } else if (trimCmd.startsWith("clearall")) {
                result = new ClearAll(trimCmd).exec(userId);
                sendToClient(session, result.toString());
            }
            else {
                result = "命令不支持";
                sendToClient(session, result.toString());
            }
        }
    }

    public void sendToClient(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message + Constant.LINE_SEPARATOR));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
