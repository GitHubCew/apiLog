package api.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
@Controller
public class WebController {

    @GetMapping("/alog-terminal")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 重定向到静态HTML的真实路径
        response.sendRedirect(request.getContextPath() + "/alog-terminal/alog-terminal.html");

    }
}
