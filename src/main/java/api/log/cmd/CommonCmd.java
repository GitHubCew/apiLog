package api.log.cmd;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public class CommonCmd implements Cmd{

    protected String cmd;

    protected String[] args;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public CommonCmd (String args) {

        String[] parts = args.split("\\s+");

        // 命令
        String command = parts.length > 0 ? parts[0] : "";

        // 参数
        String[] arguments = parts.length > 1
                ? Arrays.copyOfRange(parts, 1, parts.length)
                : new String[0];
        this.cmd = command;
        this.args = arguments;
    }

    @Override
    public String check(String[] args){
        return null;
    }

    @Override
    public Object execute(String user, String cmd, String[] args) {
        return null;
    }

    public Object exec(String user) {

        try {
            String check = check(this.args);
            if (!Objects.isNull(check) && !check.isEmpty()) {
                return check;
            }
            Object result = execute(user, this.cmd, this.args);
            return result.equals("") ? "success" : result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
