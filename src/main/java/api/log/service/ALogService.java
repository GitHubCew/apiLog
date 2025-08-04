package api.log.service;

import api.log.aop.Cache;
import api.log.base.R;
import api.log.execp.ALogException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/8/4
 */
@Component
public class ALogService {

    /**
     * 新增方法
     * @param uri 接口路径
     */
    public void addMethod (String uri) {
        Cache.put(checkAndGet(uri));
    }

    /**
     * 删除方法
     * @param uri 接口路径
     */
    public void removeMethod (@NonNull String uri) {
        Cache.remove(checkAndGet(uri));
    }

    /**
     * 清空方法
     */
    public void clearAll () {
        Cache.clear();
    }

    /**
     * 获取方法map
     * @param uri
     * @return
     */
    public R<Map<String, Method>> getUris(String uri) {
        if (Objects.isNull(uri) || uri.isEmpty()) {
            return R.ok(Cache.methodMap);
        }
        Method method = checkAndGet(uri);
        Map<String, Method> methodMap = new java.util.HashMap<>();
        methodMap.put(uri, method);
        return R.ok(methodMap);
    }

    /**
     * 校验并获取方法
     * @param uri uri
     * @return
     */
    private Method checkAndGet (String uri) {
        Method method = Cache.methodMap.get(uri);
        if (Objects.isNull(method)) {
            throw new ALogException("Url not match any method");
        }
        return method;
    }
}
