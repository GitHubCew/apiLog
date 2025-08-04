package api.log.base;

/**
 * @Desc: 结果集对象
 * @author: envi
 * @date: 2025/07/10
 */
public class R<T> {


    private static final int SUCCESS = 200;

    private static final int FAILURE = 1000;

    private Integer code;

    private T data;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(SUCCESS);
        r.setMessage("Success!");
        return r;
    }

    public static <T> R<T> ok(T data) {
        return success(SUCCESS, data, "Success");
    }

    public static <T> R<T> ok(T data, String msg) {
        return success(SUCCESS, data, msg);
    }

    public static <T> R<T> success(Integer code, T data, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> failure() {
        R<T> r = new R<>();
        r.setCode(FAILURE);
        r.setMessage("Failure!");
        return r;
    }

    public static <T> R<T> failure(String msg) {
        R<T> r = new R<>();
        r.setCode(FAILURE);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> failure(Integer code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> failure(Integer code, T object, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(object);
        r.setMessage(msg);
        return r;
    }

}
