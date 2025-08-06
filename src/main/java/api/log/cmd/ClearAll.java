package api.log.cmd;

import api.log.cache.Cache;

/**
 * @author: Envi
 * @date: 2025/8/6
 */
public class ClearAll extends CommonCmd{

    public ClearAll(String args) {
        super(args);
    }

    @Override
    public String check(String[] args) {
        if (args.length != 0) {
            return "参数错误";
        }
        return "";
    }

    @Override
    public Object execute(String user, String cmd, String[] args) {
        Cache.clearUser(user);
        return "success";
    }
}
