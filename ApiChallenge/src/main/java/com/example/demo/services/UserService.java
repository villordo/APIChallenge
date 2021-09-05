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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Value("${app.sendgrid.template}")
    private String templateId;

    //@Autowired
    //SendGrid sendGrid;

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

        //sendEmail(user.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(User.fromPostDto(user));
    }

    /*public void sendEmail(String email){
        try{
            Mail mail = prepareMail(email);

            Request request =  new Request();

            request.setMethod(Method.POST);

            request.setEndpoint("/register");

            request.setBody(mail.build());

            Response response = sendGrid.api(request);
            if(response != null){
                System.out.println("response code from sendgrid = " + response.getHeaders());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private Mail prepareMail(String email) {
        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail("villordo235@gmail.com");
        Email to = new Email();
        to.setEmail(email);
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.setTemplateId(templateId);
        return mail;
    }*/
}
