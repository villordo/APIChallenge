package com.example.demo.services;

import com.example.demo.configurations.JwtUtil;
import com.example.demo.configurations.UserConfigurationToken;
import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.User;
import com.example.demo.models.dtos.PostUsersDto;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        return new UserConfigurationToken(user.getUsername(),user.getPassword(), JwtUtil.getGrantedAuthority(user.getRol()));
    }

    public User save(PostUsersDto user) throws NotValidRolException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(User.fromPostDto(user));
    }


}
