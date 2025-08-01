package api.log.controller;

import api.log.aop.Cache;
import api.log.execp.ALogException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */

@RestController
@RequestMapping("alog")
public class ALogController {

    @GetMapping("/add")
    public void add (@NonNull String uri) {
        Method method = Cache.methodMap.get(uri);
        if (Objects.isNull(method)) {
            throw new ALogException("Url not match any method");
        }
        Cache.put(method);
    }

    @GetMapping("/remove")
    public void remove (@NonNull String uri) {
        Method method = Cache.methodMap.get(uri);
        if (Objects.isNull(method)) {
            throw new ALogException("Url not match any method");
        }
        Cache.remove(method);
    }

    @GetMapping("/clear")
    public void clear (@NonNull String uri) {
        Cache.clear();
    }
}
