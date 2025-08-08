package api.log.cmd;

import api.log.cache.Cache;

/**
 * 移除命令
 * @author  chenenwei
 */
public class Remove extends CommonCmd{

    /**
     * 构造函数
     * @param args 参数
     */
    public Remove(String args) {
        super(args);
    }

    /**
     * 检查参数是否合法
     * @param args 参数
     * @return 错误信息
     */
    @Override
    public String check(String[] args) {
        if (args.length != 1) {
            return "单次只能移除一个接口";
        }
        boolean has = Cache.hasUri(args[0]);
        if (!has) {
            return "接口不存在";
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
    public Object execute(String user, String cmd, String[] args) {
        Cache.removeMethod(user, Cache.getMethod(args[0]));
        return "success";
    }
}
