package api.log;

import api.log.aop.MethodAdvice;
import api.log.aop.Cache;
import api.log.aop.MethodPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Envi
 * @date: 2025/7/29
 */
public class TestApi {

    @org.junit.Test
    public void test() throws NoSuchMethodException {

        // 创建目标对象
        ApiController target = new ApiController();

        // 创建自定义 Pointcut
        MethodPointcut pointcut = new MethodPointcut();

        Cache.put(ApiController.class.getMethod("simple", String.class)); // 打印方法
        Cache.put(ApiController.class.getMethod("complex", String.class, int.class));
        Cache.put(ApiController.class.getMethod("complex", String.class, Map.class));

        MethodAdvice advice = new MethodAdvice();
        // 创建 Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        // 创建代理工厂并配置
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        ApiController proxy = (ApiController) proxyFactory.getProxy();

        // 测试调用
        proxy.simple("张三");
        proxy.complex("复杂测试", 18);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "18");
        proxy.complex("复合测试", map);
    }
}
