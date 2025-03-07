package web.config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("web")
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final SessionFactory sessionFactory;

    public WebConfig(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        sessionFactory = new org.hibernate.cfg.Configuration()
                .setProperty(JAKARTA_JDBC_URL, environment.getProperty("db.url"))
                .setProperty(JAKARTA_JDBC_USER, environment.getProperty("db.username"))
                .setProperty(JAKARTA_JDBC_PASSWORD, environment.getProperty("db.password"))
                .setProperty(JAKARTA_JDBC_DRIVER, environment.getProperty("db.driver"))
                .setProperty(CURRENT_SESSION_CONTEXT_CLASS, environment.getProperty("hibernate.current_session_context_class"))
                .setProperty(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl_auto"))
                .setProperty(SHOW_SQL, environment.getProperty("hibernate.show_sql"))
                .setProperty(FORMAT_SQL, environment.getProperty("hibernate.format_sql"))
                .setProperty(HIGHLIGHT_SQL, environment.getProperty("hibernate.highlight_sql"))
                .buildSessionFactory();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    @Scope("prototype")
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
