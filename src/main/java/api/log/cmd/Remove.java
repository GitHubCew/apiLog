package api.log.cmd;

import api.log.cache.Cache;

/**
 * @author: chenenwei
 * @date: 2025/8/6
 */
public class Remove extends CommonCmd{

    public Remove(String args) {
        super(args);
    }

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

    @Override
    public Object execute(String user, String cmd, String[] args) {
        Cache.removeMethod(user, Cache.getMethod(args[0]));
        return "success";
    }
}
