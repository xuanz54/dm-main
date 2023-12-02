package sut.edu.zyp.dormitory.manage.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.persistence.Entity;
import java.util.Set;

/***
 * 这是一个Spring MVC的拦截器类。实现了HandlerInterceptor接口。 其中preHandle方法会在请求处理之前进行拦截,判断session中是否有user和userType信息,如果有,说明已登录,放行请求;如果不存在,则还未登录,进行拦截,跳转到登录页面。 该类的作用是处理登录拦截,对未登录的请求进行拦截。
 */

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
                final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
                provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
                final Set<BeanDefinition> beans = provider.findCandidateComponents("sut.edu.zyp.dormitory.manage.entity");
                for (final BeanDefinition bean : beans) {
                    try {
                        config.exposeIdsFor(Class.forName(bean.getBeanClassName()));
                    } catch (final ClassNotFoundException e) {
                        throw new IllegalStateException("Failed to expose `id` field due to", e);
                    }
                }
            }
        };
    }

    @Bean
    public MappedInterceptor dataRestMappedInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, new String[]{
                "/login",
                "/captcha",
                "/login.html",
                "/400.html",
                "/500.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.woff2",
                "/**/*.ttf",
                "/**/*.eot",
                "/**/*.otf",
                "/**/*.svg",
                "/**/*.less",
                "/**/*.scss",
                "/**/*.jpg",
                "/**/*.ico",
                "/**/*.jpeg",
                "/**/*.png",
                "/**/*.bmp"}, new sut.edu.zyp.dormitory.manage.config.LoginInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new sut.edu.zyp.dormitory.manage.config.LoginInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //添加不拦截路径
        registration.excludePathPatterns(
                "/login",
                "/captcha",
                "/login.html",
                "/400.html",
                "/500.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.woff",
                "/**/*.woff2",
                "/**/*.ttf",
                "/**/*.eot",
                "/**/*.otf",
                "/**/*.svg",
                "/**/*.less",
                "/**/*.scss",
                "/**/*.jpg",
                "/**/*.ico",
                "/**/*.jpeg",
                "/**/*.png",
                "/**/*.bmp"
        );
    }
}
