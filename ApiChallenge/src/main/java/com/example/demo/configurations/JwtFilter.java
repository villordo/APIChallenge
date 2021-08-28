package com.example.demo.configurations;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.example.demo.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    private UserRepository userRepository;

    public JwtFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = this.getAuthentication((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private Authentication getAuthentication(HttpServletRequest request) throws NotValidRolException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            String userName = Jwts.parser()
                    .setSigningKey("Georgie")
                    .parseClaimsJws(token.replace("Bearer",""))
                    .getBody()
                    .getSubject();

            if(userName != null) {
                User userFilter = userRepository.findByUsername(userName);
                UserConfigurationToken principal = new UserConfigurationToken(userFilter.getUsername(), userFilter.getPassword(), JwtUtil.getGrantedAuthority(userFilter.getRol()));

                return new UsernamePasswordAuthenticationToken(userFilter, null, principal.getAuthorities());

            }

        }
        return null;
    }
}
