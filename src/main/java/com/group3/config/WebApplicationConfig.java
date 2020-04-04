package com.group3.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
public class WebApplicationConfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher" ,
                new DispatcherServlet(ctx));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
    }
}
