package api.log.outer;

import api.log.base.OutContent;

import java.lang.reflect.Method;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */
public interface Outer {

    void out(Method method, OutContent outContent);
}
