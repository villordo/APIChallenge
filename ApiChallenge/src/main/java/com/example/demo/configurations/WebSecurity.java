package com.example.demo.configurations;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity

public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userDetailsService;
    private UserRepository userRepository;

    public WebSecurity(UserService userPrincipalDetailsService, UserRepository userRepository) {
        this.userDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService); // aca podria encriptar la contraseña
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new LoginFilter(authenticationManager()))
                .addFilter(new JwtFilter(authenticationManager(),  this.userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/characters").authenticated()
                .antMatchers("/movies").hasAnyRole("client", "admin")
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated();
    }
}
