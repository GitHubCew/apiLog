package api.log.config;

import api.log.aop.MethodAdvice;
import api.log.aop.MethodPointcut;
import api.log.formater.DefaultParamFormatter;
import api.log.formater.ParamFormatter;
import api.log.outer.DefaultOuter;
import api.log.outer.Outer;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

@Configuration
@EnableAspectJAutoProxy
public class ALogConfig implements ImportBeanDefinitionRegistrar{

    @Order()
    @Bean
    public MethodPointcut pointcut() {return new MethodPointcut();}

    @Bean
    public MethodAdvice advice() {return new MethodAdvice();}

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut(), advice());
        advisor.setOrder(Integer.MAX_VALUE);
        return advisor;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        registerBean(registry);
        scanPackages(registry, "api.log");
    }

    private void registerBean(BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder defaultParamFormatterBuilder = BeanDefinitionBuilder
                .rootBeanDefinition(DefaultParamFormatter.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON);

        if (!registry.containsBeanDefinition("defaultParamFormatter")) {
            registry.registerBeanDefinition("defaultParamFormatter", defaultParamFormatterBuilder.getBeanDefinition());
        }

        BeanDefinitionBuilder defaultOuterBuilder = BeanDefinitionBuilder
                .rootBeanDefinition(DefaultOuter.class)
                .setScope(BeanDefinition.SCOPE_SINGLETON);

        if (!registry.containsBeanDefinition("defaultOuter")) {
            registry.registerBeanDefinition("defaultOuter", defaultOuterBuilder.getBeanDefinition());
        }
    }


    private void scanPackages(BeanDefinitionRegistry registry, String... basePackages) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.scan(basePackages);
    }

}
