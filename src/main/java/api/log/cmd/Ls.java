package api.log.cmd;

import api.log.base.Constant;
import api.log.cache.Cache;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class Ls extends CommonCmd{

    public Ls(String args) {
        super(args);
    }

    @Override
    public String check(String[] args) {

        if (args.length != 0 && args.length != 1) {
            return "参数错误: 格式为：ls [接口路径]";
        }
        return "";
    }

    @Override
    public String execute(String user, String cmd, String[] args) {
        List<String> sorted = Cache.methodCache.keySet().stream().sorted().collect(Collectors.toList());
        if (args.length == 0) {
            return String.join(Constant.LINE_SEPARATOR, sorted);
        }
        return sorted.stream().filter(key -> key.contains(args[0])).collect(Collectors.joining(Constant.LINE_SEPARATOR));
    }
}

