package api.log.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author: chenenwei
 * @date: 2025/8/5
 */
@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class ALogResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 强制注册资源路径（即使主项目没有配置）
        registry.addResourceHandler("/alog-terminal/**")
                .addResourceLocations("classpath:/META-INF/resources/alog-terminal/")
                .setCachePeriod(3600)
                .resourceChain(true);
    }
}
