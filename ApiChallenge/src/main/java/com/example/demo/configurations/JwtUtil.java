package com.example.demo.configurations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class JwtUtil {
    static void addAuthentication(HttpServletResponse response,String username){
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256,"Georgie")
                .setExpiration(new Date(System.currentTimeMillis() + 300_000))
                .compact();
        response.addHeader("Authorization","Bearer"+token);
    }
    static Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("Georgie")
                    .parseClaimsJws(token.replace("Bearer",""))
                    .getBody()
                    .getSubject();
            return  user != null?
                    User user = userRepository.findByUsername(user);
                    UserConfigurationToken principal = new UserConfigurationToken(user.getUsername(), user.getPassword(), GrantedAuthorities.getGrantedAuthority(user.getRol()));

            return new UsernamePasswordAuthenticationToken(user, null, principal.getAuthorities());

        }
        return null;
    }
}
