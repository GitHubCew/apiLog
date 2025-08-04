package api.log.controller;

import api.log.base.R;
import api.log.service.ALogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: chenenwei
 * @date: 2025/7/31
 */

@RestController
@RequestMapping("alog")
public class ALogController {

    @Autowired
    private ALogService aLogService;

    @GetMapping("/add")
    public R<Boolean> add (@NonNull String uri) {
        aLogService.addMethod(uri);
        return R.ok(true);
    }

    @GetMapping("/remove")
    public R<Boolean> remove (@NonNull String uri) {
      aLogService.removeMethod(uri);
      return R.ok(true);
    }

    @GetMapping("/clear")
    public R<Boolean> clear (@NonNull String uri) {
        aLogService.clearAll();
        return R.ok(true);
    }

    @GetMapping("/getUris")
    public R<Map<String, Method>> getUris (String uri) {
        return aLogService.getUris(uri);
    }
}
