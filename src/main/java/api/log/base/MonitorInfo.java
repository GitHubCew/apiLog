package api.log.base;

import java.lang.reflect.Method;

/**
 * 方法监测信息类
 * @author  chenenwei
 */
public class MonitorInfo {

    /**
     * 方法
     */
    private Method method;
    /**
     * 参数
     */
    private boolean param;
    /**
     * 结果
     */
    private boolean result;
    /**
     * 时间
     */
    private boolean time;

    /**
     * 构造方法
     */
    public MonitorInfo() {
    }

    /**
     * 构造方法
     * @param method 方法
     * @param param 参数
     * @param result 结果
     * @param time 时间
     */
    public MonitorInfo (Method method, boolean param, boolean result, boolean time) {
        this.method = method;
        this.param = param;
        this.result = result;
        this.time = time;
    }

    /**
     * 获取方法
     * @return
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 设置方法
     * @param method 方法
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 获取参数
     * @return 参数
     */
    public boolean isParam() {
        return param;
    }

    /**
     * 设置参数
     * @param param 参数
     */
    public void setParam(boolean param) {
        this.param = param;
    }

    /**
     * 获取结果
     * @return 结果
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置结果
     * @param result 结果
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取时间
     * @return 时间
     */
    public boolean isTime() {
        return time;
    }

    /**
     * 设置时间
     * @param time 时间
     */
    public void setTime(boolean time) {
        this.time = time;
    }
}
