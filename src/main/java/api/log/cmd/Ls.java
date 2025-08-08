package api.log.cmd;

import api.log.base.Constant;
import api.log.cache.Cache;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 列出接口命令
 * @author  chenenwei
 */
public class Ls extends CommonCmd{

    /**
     * 构造函数
     * @param args 参数
     */
    public Ls(String args) {
        super(args);
    }

    /**
     * 检查参数是否正确
     * @param args 参数
     * @return 错误信息
     */
    @Override
    public String check(String[] args) {

        if (args.length != 0 && args.length != 1) {
            return "参数错误: 格式为：ls [接口路径]";
        }
        return "";
    }

    /**
     * 执行命令
     * @param user 用户
     * @param cmd 命令
     * @param args 参数
     * @return 执行结果
     */
    @Override
    public String execute(String user, String cmd, String[] args) {
        List<String> sorted = Cache.methodCache.keySet().stream().sorted().collect(Collectors.toList());
        if (args.length == 0) {
            return String.join(Constant.LINE_SEPARATOR, sorted);
        }
        return sorted.stream().filter(key -> key.contains(args[0])).collect(Collectors.joining(Constant.LINE_SEPARATOR));
    }
}

