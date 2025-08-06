package api.log.aop;

import api.log.cache.Cache;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author: chenenwei
 * @date: 2025/7/29
 */
public class MethodPointcut implements Pointcut{

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz.getSimpleName().toLowerCase().endsWith("controller");
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 静态检查 - 代理创建时执行
                // 这里返回true表示所有方法都可能在运行时被检查
                return true;
            }

            @Override
            public boolean isRuntime() {
                // 返回true表示每次方法调用都会检查
                return true;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                // 运行时检查 - 每次方法调用都执行
                return Cache.hasMethod(method);
            }
        };
    }
}
