package edu.csumb.cst438.EnFrame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.cst438.EnFrame.models.User;
import edu.csumb.cst438.EnFrame.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public Boolean addUser(User user){
        return userRepo.insertIfExist(user);
    }
    public Iterable<User> getAllUsers(){
        return(userRepo.findAll());
    }

    public void checkCredentials(String email, String password){
        User user = userRepo.findById(email).get();
        user.authUsername(email, password);
//        userRepository.save(user);
    }


}