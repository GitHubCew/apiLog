package api.log.cmd;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
public interface Cmd {

    String check(String[] args);

    Object execute (String user, String command, String[] args);
}
