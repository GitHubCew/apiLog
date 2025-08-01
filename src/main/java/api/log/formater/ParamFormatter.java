package api.log.formater;

import java.lang.reflect.Parameter;

/**
 * @author: chenenwei
 * @date: 2025/7/29
 */
public interface ParamFormatter {

    /**
     * 参数格式化
     * @param parameters 参数列表
     * @param parameterValues 参数值列表
     * @return
     */
    Object format(Parameter[] parameters, Object[] parameterValues);
}
