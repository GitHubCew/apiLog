package api.log.outer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */
public class DefaultOuter implements Outer{

    private final Log logger = LogFactory.getLog(DefaultOuter.class);

    @Override
    public void print(Object object) {
        logger.info("【API LOG PARAMETER】 ") ;
        logger.info(object);
    }
}
