package api.log.base;

/**
 * @author: chenenwei
 * @date: 2025/8/4
 */
public class OutContent {

    /**
     * 接口参数
     */
    private Object param;

    /**
     * 接口耗时（毫秒）
     */
    private Long time;

    /**
     * 接口返回结果
     */
    private Object result;

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
