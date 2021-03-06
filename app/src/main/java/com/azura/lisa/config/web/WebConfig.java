package com.azura.lisa.config.web;

import com.azura.lisa.config.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Value("${static.file.location}")
	private String rootDirectory;
	
	@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(1000000);
    }
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(byteArrayHttpMessageConverter());
	}

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
		return arrayHttpMessageConverter;
	}

	private List<MediaType> getSupportedMediaTypes() {
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.IMAGE_JPEG);
		list.add(MediaType.IMAGE_PNG);
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		list.add(MediaType.APPLICATION_PDF);
		return list;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!rootDirectory.endsWith("/")) {
			rootDirectory += "/";
		}
		registry.addResourceHandler("/private/**").addResourceLocations("file://" + rootDirectory).setCachePeriod(5);
	}

	@Configuration
    @Order(1)
    public static class TokenSecurityConfiguration extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").httpBasic().and()
				.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
				.csrf().disable().authorizeRequests()
				.antMatchers(
						new String[]{
							"/public/**", "/css/**",
							"/js/**",
                                "/images/**",
                                "/index.html",
                                "/",
                                "/page/**",
							"/api/user/login",
                                "/api/edu/show/**",
							"/api/user/verifyToken",
							"/api/user/signup",
							"/img/**",
								"/api/lehoang/excel/**",
                                "/api/tutorial/show/**",
                                "/api/tutorial/updateView",
								"/api/eduFollow/show/**",
								"/api/like/total",
                                "/api/like/filter",
								"/api/search",
								"/api/tutorialComment/show/**"
						})
				.permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
	}

}
