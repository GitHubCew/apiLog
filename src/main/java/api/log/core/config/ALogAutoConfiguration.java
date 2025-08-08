package api.log.core.config;

import api.log.aop.MethodAdvice;
import api.log.aop.MethodPointcut;
import api.log.business.formater.DefaultParamFormatter;
import api.log.business.outer.WebSocketOuter;
import api.log.business.socket.SocketHandler;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.Collections;
import java.util.HashMap;

/**
 * 自动配置类
 */
@EnableWebSocket
@Configuration
@EnableAspectJAutoProxy
public class ALogAutoConfiguration implements ImportBeanDefinitionRegistrar, WebSocketConfigurer {

    @Qualifier("alogSocketHandler")
    @Autowired
    private SocketHandler socketHandler; // 直接注入

    /**
     * 切点
     * @return 切点
     */
    @Bean
    public MethodPointcut pointcut() {return new MethodPointcut();}

    /**
     * 通知
     * @return 通知
     */
    @Bean
    public MethodAdvice advice() {return new MethodAdvice();}

    /**
     * 默认切面
     * @return 切面
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut(), advice());
        advisor.setOrder(Integer.MAX_VALUE);
        return advisor;
    }

    @Order(0)
    @Bean
    public WebMvcConfigurer alogTerminalWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/alog/**")
                        .addResourceLocations("classpath:/META-INF/resources/alog/")
                        .resourceChain(true);
            }
        };
    }

    /**
     * 注册BeanDefinition
     * @param importingClassMetadata importingClassMetadata
     * @param registry registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        registerBean(registry);
        scanPackages(registry, "api.log");
    }

    /**
     * 注册WebSocketHandler
     * @param registry registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/alog-ws").setAllowedOrigins("*");
    }

    /**
     * 注册Bean
     * @param registry registry
     */
    private void registerBean(BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder defaultParamFormatterBuilder = BeanDefinitionBuilder
                .rootBeanDefinition(DefaultParamFormatter.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON);

        if (!registry.containsBeanDefinition("defaultParamFormatter")) {
            registry.registerBeanDefinition("defaultParamFormatter", defaultParamFormatterBuilder.getBeanDefinition());
        }

        BeanDefinitionBuilder defaultOuterBuilder = BeanDefinitionBuilder
                .rootBeanDefinition(WebSocketOuter.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON);

        if (!registry.containsBeanDefinition("webSocketOuter")) {
            registry.registerBeanDefinition("webSocketOuter", defaultOuterBuilder.getBeanDefinition());
        }

    }

    /**
     * 扫描包
     * @param registry registry
     * @param basePackages basePackages
     */
    private void scanPackages(BeanDefinitionRegistry registry, String... basePackages) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.scan(basePackages);
    }
}
