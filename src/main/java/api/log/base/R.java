package api.log.base;

/**
 * 结果集对象
 * @author chenenwei
 */
public class R<T> {

    /**
     * 成功码
     */
    private static final int SUCCESS = 200;

    /**
     * 失败码
     */
    private static final int FAILURE = 1000;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误描述
     */
    private String message;

    /**
     * 获取错误码
     * @return 错误码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置错误码
     * @param code 错误码
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取结果
     * @return 结果
     */
    public T getData() {
        return data;
    }

    /**
     * 设置结果
     * @param data 结果
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取错误描述
     * @return 错误描述
     */
    public String getMessage() {
        return message;
    }

    /**
     *  设置错误描述
     * @param message 错误描述
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 成功
     * @param <T> 结果类型
     * @return 返回值
     */
    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(SUCCESS);
        r.setMessage("Success!");
        return r;
    }

    /**
     * 成功
     * @param data 数据
     * @param <T> 结果类型
     * @return 返回值
     */
    public static <T> R<T> ok(T data) {
        return success(SUCCESS, data, "Success");
    }

    /**
     * 成功
     * @param data 数据
     * @param msg 错误描述
     * @param <T> 结果类型
     * @return 返回值
     */
    public static <T> R<T> ok(T data, String msg) {
        return success(SUCCESS, data, msg);
    }

    /**
     * 成功
     * @param code 错误码
     * @param data 数据
     * @param msg 错误描述
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> R<T> success(Integer code, T data, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMessage(msg);
        return r;
    }

    /**
     * 失败
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> R<T> failure() {
        R<T> r = new R<>();
        r.setCode(FAILURE);
        r.setMessage("Failure!");
        return r;
    }

    /**
     * 失败
     * @param msg 错误描述
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> R<T> failure(String msg) {
        R<T> r = new R<>();
        r.setCode(FAILURE);
        r.setMessage(msg);
        return r;
    }

    /**
     * 失败
     * @param code 错误码
     * @param msg 错误描述
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> R<T> failure(Integer code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    /**
     * 失败
     * @param code 错误码
     * @param object 结果对象
     * @param msg 错误描述
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> R<T> failure(Integer code, T object, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(object);
        r.setMessage(msg);
        return r;
    }

}
