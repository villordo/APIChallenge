package com.example.demo.configurations;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.enums.Rol;
import com.example.demo.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;

public class JwtUtil {


    static void addAuthentication(HttpServletResponse response, String username){
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256,"Georgie")
                .setExpiration(new Date(System.currentTimeMillis() + 300_000))
                .compact();
        response.addHeader("Authorization","Bearer"+token);
    }
    public static List<GrantedAuthority> getGrantedAuthority(Rol rol) throws NotValidRolException {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (rol.equals(Rol.client)){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + Rol.client.toString()));
        } else if (rol.equals(Rol.admin)){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + Rol.client.toString()));
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + Rol.admin.toString()));
        }  else {
            throw new NotValidRolException("Invalid rol");
        }

        return grantedAuthorities;
    }

}
