package com.azura.lisa.config;

import com.azura.lisa.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Slf4j
public class TokenAuthenticationHelper {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ix9O77EDipXKswqftzjx1Wr8MP0gpImT";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static String generateToken(Object user) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String token = Jwts.builder()
                    .setSubject(mapper.writeValueAsString(user))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            return TOKEN_PREFIX + " " + token;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        return getAuthenticationByToken(token);
    }

    public static Authentication verifyToken(String token) {
        return getAuthenticationByToken(token);
    }

    private static Authentication getAuthenticationByToken(String token) {
        if (token == null || !token.startsWith(TOKEN_PREFIX)) return null;
        Authentication auth = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            auth = new UsernamePasswordAuthenticationToken(mapper.readValue(user, User.class), null, Collections.emptyList());
        } catch (Exception e) {
            log.error("Invalid token !");
        }
        return auth;
    }
}