package com.hrp.spring.config.initializers;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


import com.hrp.security.OAuth2ServerConfiguration;
import com.hrp.security.WebSecurityConfig;
import com.hrp.spring.config.AppConfig;


public class SpringMvcInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppConfig.class, WebSecurityConfig.class, OAuth2ServerConfiguration.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
	}*/
	
}