package bbs.util;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@SuppressWarnings("serial")
public class MyDispatcherServlet extends DispatcherServlet implements ApplicationListener<ContextRefreshedEvent> {

    private static ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        applicationContext = contextRefreshedEvent.getApplicationContext();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ResourceHttpRequestHandler handler = applicationContext.getBean(ResourceHttpRequestHandler.class);
        handler.setSupportedMethods(ResourceHttpRequestHandler.METHOD_GET, ResourceHttpRequestHandler.METHOD_HEAD,
                ResourceHttpRequestHandler.METHOD_POST);
    }
}