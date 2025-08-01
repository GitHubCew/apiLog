package api.log.aop;

import api.log.formater.DefaultParamFormatter;
import api.log.formater.ParamFormatter;
import api.log.outer.Outer;
import api.log.utils.ContextUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (printParam) {
            logger.debug("Before method execute...");
            // 参数格式化
            Object format = formatter.format(invocation.getMethod().getParameters(), invocation.getArguments());
            // 参数输出，默认使用spring
            Outer outer = ContextUtil.getBean(Outer.class);
            // 本地测试使用
//            Outer outer = new DefaultOuter();
            outer.print(format);
        }
        Object result = invocation.proceed();
        if (printParam) {
            logger.debug("After method executed...");
        }
        return result;
    }
}
