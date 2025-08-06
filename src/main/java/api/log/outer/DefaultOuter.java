package api.log.outer;

import api.log.base.OutContent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */
public class DefaultOuter implements Outer {

    private final Log logger = LogFactory.getLog(DefaultOuter.class);

    @Override
    public void out(Method method, OutContent content) {
        logger.info("【API PARAM】:" + content.getParam());
        logger.info("【API RESULT】:" + content.getResult());
        logger.info("【API TIME】:" + content.getTime());
    }
}
