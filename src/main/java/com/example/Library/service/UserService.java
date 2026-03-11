package com.example.Library.service;



import com.example.Library.model.User;
import com.example.Library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            return null;
        }else{
            return optionalUser.get();
        }

    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
