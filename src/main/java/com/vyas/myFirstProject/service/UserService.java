package com.vyas.myFirstProject.service;

import com.vyas.myFirstProject.entity.User;
import com.vyas.myFirstProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public void saveNewUser(User userEntry){
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(List.of("USER"));
            userRepository.save(userEntry);
        } catch (Exception e) {
            log.info("Hahahahahhaha");
            log.debug("Hahahahahhaha");
            log.error("Hahahahahhaha");
            log.trace("Hahahahahhaha");
        }
    }
    public void saveUser(User user){
        userRepository.save(user);
    }



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteEntry(ObjectId id){ userRepository.deleteById(id);}


    public User findByUsername(String userName){
        return userRepository.findByUserName(userName);
    }


    public void saveAdmin(User userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(userEntry);
    }
}
