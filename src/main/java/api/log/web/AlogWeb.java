package api.log.web;

import api.log.core.Constant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

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
    public ResponseEntity<byte[]> alogTerminal() throws IOException {
        ClassPathResource resource = new ClassPathResource(Constant.ALOG_TERMINAL_HTML);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        byte[] fileContent = StreamUtils.copyToByteArray(resource.getInputStream());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(fileContent);
    }
}
