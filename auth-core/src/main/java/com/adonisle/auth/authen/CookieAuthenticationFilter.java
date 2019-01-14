package com.adonisle.auth.authen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.adonisle.auth.model.Auth;
import com.adonisle.auth.service.AuthService;
import com.adonisle.auth.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;


public class CookieAuthenticationFilter extends GenericFilterBean {
    private static final Logger log = LoggerFactory.getLogger(CookieAuthenticationFilter.class);
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    public CookieAuthenticationFilter(PasswordEncoder passwordEncoder, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = this.getAuthentication((HttpServletRequest)request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        if (request.getRequestURI().toLowerCase().startsWith("/public")) {
            return null;
        } else {
            String email = this.getCookie("currentUserEmail", request);
            String password = this.getCookie("currentUserPassword", request);
            log.info("(getAuthentication)email: " + email);
            if (email != null && password != null) {
                try {
                    Auth auth = this.authService.findByEmail(email);

                    if (auth != null && this.passwordEncoder.matches(password, auth.getPassword())) {
                        List<GrantedAuthority> authorities = new ArrayList();
                        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                        return new UsernamePasswordAuthenticationToken(auth, (Object)null, authorities);
                    }
                } catch (Exception var6) {
                    log.error(ExceptionUtil.getStackTrace(var6));
                }
            }

            return null;
        }
    }

    private String getCookie(String name, HttpServletRequest request) {
        if (request.getCookies() != null) {
            Cookie[] var3 = request.getCookies();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}