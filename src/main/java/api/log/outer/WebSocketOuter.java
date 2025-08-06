package api.log.outer;

import api.log.base.Constant;
import api.log.base.ContextUtil;
import api.log.base.MonitorInfo;
import api.log.base.OutContent;
import api.log.cache.Cache;
import api.log.socket.SessionContext;
import api.log.socket.SessionManager;
import api.log.socket.SocketHandler;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class WebSocketOuter implements Outer{

    @Override
    public void out(Method method, OutContent outContent) {

        Map<String, MonitorInfo> monitorOfUser = Cache.getUsersByMethod(method);
        monitorOfUser.forEach((userId, monitorInfo) -> {
            try {
                SessionContext session = ContextUtil.getBean(SessionManager.class).getSession(userId);
                if (!session.getSession().isOpen()) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                if (monitorInfo.isParam()) {
                    sb.append(outContent.getParam()).append(Constant.SEPARATOR);
                }
                if (monitorInfo.isResult()) {
                    sb.append(outContent.getResult()).append(Constant.SEPARATOR);
                }
                if (monitorInfo.isTime()) {
                    sb.append(outContent.getTime());
                }
                // 发送消息
                ContextUtil.getBean(SocketHandler.class).sendToClient(session.getSession(), sb.toString().replaceAll(Constant.SEPARATOR, Constant.LINE_SEPARATOR));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
