package com.example.demo.services;

import com.example.demo.configurations.JwtUtil;
import com.example.demo.configurations.UserConfigurationToken;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.User;
import com.example.demo.models.dtos.PostUserDto;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepository.findByUsername(s);
        User user = userOpt.get();

        return new UserConfigurationToken(user.getUsername(),user.getPassword(), JwtUtil.getGrantedAuthority(user.getRol()));
    }

    public User save(PostUserDto user) throws NotValidRolException, AlreadyExistsException {
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new AlreadyExistsException("This user already exists");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(User.fromPostDto(user));
    }


}
