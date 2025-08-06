package api.log.base;

import java.lang.reflect.Method;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class MonitorInfo {

    private Method method;
    private boolean param;
    private boolean result;
    private boolean time;

    public MonitorInfo() {
    }

    public MonitorInfo (Method method, boolean param, boolean result, boolean time) {
        this.method = method;
        this.param = param;
        this.result = result;
        this.time = time;
    }
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public boolean isParam() {
        return param;
    }

    public void setParam(boolean param) {
        this.param = param;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }
}
