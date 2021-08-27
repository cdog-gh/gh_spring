package com.example.demo.util;
import com.example.demo.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtUtil {
    @Value("${jwt.security}")
    private String securityKey;
    public String generateJwtToken(User user) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + 10 * 60 * 1000);
        
        return Jwts.builder().
                setSubject(user.getUserName())
                .setHeader(createHeader())
                .setClaims(createBody(user))
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }

    private Map<String, Object> createBody(User user) {
        Map <String, Object> body = new HashMap<>();
        body.put("userName", user.getUserName());
        body.put("userRole", user.getUserRoleName());
        return body;
    }

    private Map<String, Object> createHeader() {
        Map <String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }
}
