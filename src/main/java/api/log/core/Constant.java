package api.log.core;

import java.util.regex.Pattern;

/**
 * 常量类
 * @author  chenenwei
 */
public class Constant {


    public final static String BASE_RESOURCE_PATH = "META-INF/resources/alog/";

    public final static String ALOG_TERMINAL_HTML = BASE_RESOURCE_PATH + "alog-terminal.html";

    public static final Pattern I18N_PATTERN = Pattern.compile("\\$\\{i18n\\.(\\w+)\\}");

    public static final String SPACE_PATTERN ="\\s+";


    /**
     * 拼接符
     */
    public final static String CONCAT_SEPARATOR = "@@";

    /**
     * 换行符
     */
    public final static String LINE_SEPARATOR = "\n";

    public static final String OK = "ok";
    public static final String ERROR = "error";
    public static final String EMPTY = "";

    public static final String OUT_INFO = "[INFO] ";
    public static final String OUT_ERROR = "[ERROR] ";

}
