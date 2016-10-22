package com.hrp.spring.config;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;



@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
@PropertySource(value = "file:///${HRPConfigPath}")
@ComponentScan(basePackages = { "com.hrp.model","com.hrp.dao", "com.hrp.service", "com.hrp.servlet", "com.hrp.rest.controller",
		 })
@Import({ DataSourceConfig.class, HibernateConfiguration.class})
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public HRPConfig hrpConfig() {
		return new HRPConfig();
	}

	@Bean
	public ViewResolver configureViewResolver() {
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setOrder(2);
		viewResolve.setPrefix("/WEB-INF/home/");
		viewResolve.setSuffix(".jsp");

		return viewResolve;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.registerModule(new Hibernate4Module());
		mapper.setSerializationInclusion(Include.NON_NULL);
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(mapper);
		/*
		 * List<MediaType> jsonMediaType = new ArrayList<>();
		 * jsonMediaType.add(MediaType.APPLICATION_JSON);
		 * jsonConverter.setSupportedMediaTypes(jsonMediaType);
		 */

		converters.add(jsonConverter);

	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("images/");
		registry.addResourceHandler("/css/**").addResourceLocations("css/");
		registry.addResourceHandler("/js/**").addResourceLocations("js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("fonts/");
		registry.addResourceHandler("/home/*.html").addResourceLocations("home/");
		registry.addResourceHandler("/views/**").addResourceLocations("views/");
		registry.addResourceHandler("/user/*.html").addResourceLocations("user/");
		registry.addResourceHandler("/*.html").addResourceLocations("/");
	}
	
	
	
	// For cross origin requests(CORS): 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	/*@Bean
	public CacheManager getEhCacheManager(){
	        return  new EhCacheCacheManager(getEhCacheFactory().getObject());
	}*/
	
	/*@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("com/hrp/spring/config/ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}*/

}
