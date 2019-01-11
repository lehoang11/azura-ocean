package com.adonisle.auth.config;

import com.adonisle.auth.authen.CookieAuthenticationFilter;
import com.adonisle.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class WebConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;

    public WebConfiguration() {
    }

    protected void configure(HttpSecurity http) throws Exception {
        log.info("MY_CONFIGURATION_LMS");
        ((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)http.cors().and()).csrf().disable()).authorizeRequests().antMatchers(new String[]{"/public/**", "/swagger-resources/**", "/v2/api-docs**"})).permitAll().anyRequest()).authenticated().and()).addFilterBefore(new CookieAuthenticationFilter(this.passwordEncoder, this.authService), UsernamePasswordAuthenticationFilter.class);
    }
}
