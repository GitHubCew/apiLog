package api.log;

import java.util.Map;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */
public class ApiController {

    public Object simple (String name) {
        return "简单测试";
    }

    public Object complex (String name, int age) {
        return "复杂测试";
    }

    public Object complex (String name, Map<String, Object> info) {
        return "复合测试";
    }
}
