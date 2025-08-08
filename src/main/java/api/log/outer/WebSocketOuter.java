package api.log.outer;

import api.log.base.Constant;
import api.log.base.ContextUtil;
import api.log.base.MonitorInfo;
import api.log.base.OutContent;
import api.log.cache.Cache;
import api.log.socket.SessionContext;
import api.log.socket.SessionManager;
import api.log.socket.SocketHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * websocket输出器
 * @author  chenenwei
 */
public class WebSocketOuter implements Outer{

    /**
     * 构造方法
     */
    public WebSocketOuter () {

    }
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 输出日志
     * @param method 调用的方法
     * @param outContent 输出内容
     */
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
                    appendValue(sb, objectMapper, outContent.getParam());
                    sb.append(Constant.CONCAT_SEPARATOR);

                }
                if (monitorInfo.isResult()) {
                    appendValue(sb, objectMapper, outContent.getResult());
                    sb.append(Constant.CONCAT_SEPARATOR);

                }
                if (monitorInfo.isTime()) {
                    appendValue(sb, objectMapper, outContent.getTime());
                }
                // 发送消息
                ContextUtil.getBean(SocketHandler.class).sendToClient(session.getSession(), sb.toString().replaceAll(Constant.CONCAT_SEPARATOR, Constant.LINE_SEPARATOR));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理各种类型的值
     * @param sb 输出内容
     * @param mapper 对象序列化器
     * @param value 值
     * @throws JsonProcessingException 序列化异常
     */
    private void appendValue(StringBuilder sb, ObjectMapper mapper, Object value) throws JsonProcessingException {
        if (value == null) {
            sb.append("null");
            return;
        }

        // 基本类型直接toString
        if (value.getClass().isPrimitive() ||
                value instanceof Number ||
                value instanceof Boolean ||
                value instanceof Character ||
                value instanceof String) {
            sb.append(value);
        }
        // 数组类型
        else if (value.getClass().isArray()) {
            sb.append(Arrays.toString((Object[])value));
        }
        // 其他对象类型使用JSON序列化
        else {
            sb.append(mapper.writeValueAsString(value));
        }
    }
}
