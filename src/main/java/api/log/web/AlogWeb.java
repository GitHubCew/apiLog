package api.log.web;

import api.log.core.Constant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Alog Web
 * @author chenenwei
 */
@Controller
public class AlogWeb {

    /**
     * alog terminal
     * @return ResponseEntity
     * @throws IOException IOException
     */
    @GetMapping("/alog/alog-terminal.html")
    public ResponseEntity<byte[]> alogTerminal(HttpServletRequest request) throws IOException {
        // 获取请求的语言环境
        String acceptLanguage = request.getHeader("Accept-Language");
        boolean isChinese = isChinesePreferred(acceptLanguage);

        // 根据语言环境选择不同的HTML文件
        String htmlFile = isChinese ? Constant.ALOG_TERMINAL_ZH_HTML : Constant.ALOG_TERMINAL_HTML;

        ClassPathResource resource = new ClassPathResource(htmlFile);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileContent = StreamUtils.copyToByteArray(resource.getInputStream());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(fileContent);
    }

    // 判断是否优先使用中文
    private boolean isChinesePreferred(String acceptLanguage) {
        if (acceptLanguage == null || acceptLanguage.isEmpty()) {
            return false; // 默认英文
        }

        // 解析Accept-Language头部，示例格式: "zh-CN,zh;q=0.9,en;q=0.8"
        String[] languages = acceptLanguage.split(",");
        for (String lang : languages) {
            String[] parts = lang.split(";");
            String language = parts[0].trim().toLowerCase();

            if (language.startsWith("zh")) { // 匹配zh-CN, zh-TW, zh-HK等
                return true;
            }
            if (language.startsWith("en")) {
                return false;
            }
        }

        return false; // 默认英文
    }
}
