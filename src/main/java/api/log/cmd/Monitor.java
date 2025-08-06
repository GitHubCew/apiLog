package api.log.cmd;

import api.log.base.MonitorInfo;
import api.log.cache.Cache;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class Monitor extends CommonCmd{

    public Monitor(String args) {
        super(args);
    }

    @Override
    public String check(String[] args) {

        if (args.length != 1 && args.length != 2) {
            return "参数错误,格式为：monitor [接口路径] [param|result|time]";
        }
        if (!Cache.hasUri(args[0])) {
            return "参数错误,接口路径不存在";
        }

        if (args.length == 2) {
            List<String> apiContents = Arrays.asList("param", "result", "time");
            boolean include = Arrays.stream(args[1].split(",")).anyMatch(s -> apiContents.contains(s.trim()));
            if (!include) {
                return "参数错误,可选值 [param|result|time]" ;
            }
        }
        return "";
    }

    @Override
    public String execute(String user, String cmd, String[] args) {
        Method method = Cache.getMethod(args[0].trim());
        if (args.length == 1) {
            Cache.addMethod(user, new MonitorInfo(method, true, true, true));
        }
        else if (args.length == 2) {
            String trimCmd = args[1].trim();
            boolean param = false;
            boolean result = false;
            boolean time = false;
            if (trimCmd.contains("param")) {
                param = true;
            }
            if (trimCmd.contains("result")) {
                result = true;
            }
            if (trimCmd.contains("time")) {
                time = true;
            }
            Cache.addMethod(user, new MonitorInfo(method, param, result, time));
        }
        return "success";
    }
}
