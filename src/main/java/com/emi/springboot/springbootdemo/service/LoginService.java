package com.emi.springboot.springbootdemo.service;

import com.emi.springboot.springbootdemo.model.User;
import com.emi.springboot.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Created by Emi on 11/12/2017.
 */
@Service
public class LoginService {

    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialized(){
        User user = new User();
        user.setFirstName("Emiliano");
        user.setLastName("Castells");
        user.setUsername("emi");
        user.setPassword("test");
        userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        Optional<User> user =  Optional.ofNullable(userRepository.findByUsernameAndPassword(username,password));
        return user.isPresent();
    }

}
