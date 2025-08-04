package api.log.aop;

import api.log.base.OutContent;
import api.log.execp.ALogException;
import api.log.formater.ParamFormatter;
import api.log.base.ContextUtil;
import api.log.outer.Outer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: chenenwei
 * @date: 2025/7/29
 */
public class MethodAdvice implements MethodInterceptor {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        boolean printParam = Cache.contains(invocation.getMethod());

        ParamFormatter formatter = ContextUtil.getBean(ParamFormatter.class);

        OutContent content = new OutContent();
        Object result  = null;
        try {

            if (printParam) {
                logger.debug("Before method execute...");
                // 参数格式化
                Object format = formatter.format(invocation.getMethod().getParameters(), invocation.getArguments());
                content.setParam(format);
            }
            // 计时
            long start = System.currentTimeMillis();

            // 执行原方法
            result = invocation.proceed();

            content.setResult(result);

            // 计算方法耗时
            long spend = System.currentTimeMillis() - start;

            content.setTime(spend);

        }
        catch (Exception e) {
            throw new ALogException("接口处理失败" + e.getMessage());
        }
        finally {
            // 输出content
            Outer outer = ContextUtil.getBean(Outer.class);
            outer.out(content);
        }
        if (printParam) {
            logger.debug("After method executed...");
        }
        return result;
    }
}
