package com.example.demo.services;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.enums.Rol;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.example.demo.models.User user = userRepository.findByUsername(s);
        return new User(user.getUsername(),user.getPassword(),user.getActive(),user.getActive(),user.getActive(),user.getActive()
                ,getGrantedAuthority(user.getRol()));
    }

    public List<GrantedAuthority> getGrantedAuthority(Rol rol) throws NotValidRolException {

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
