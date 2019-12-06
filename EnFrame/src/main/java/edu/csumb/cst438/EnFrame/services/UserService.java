package edu.csumb.cst438.EnFrame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.cst438.EnFrame.models.User;
import edu.csumb.cst438.EnFrame.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    User user;

    public Boolean addUser(User user) {
        return userRepo.insertIfExist(user);
    }

    public Iterable<User> getAllUsers() {
        return (userRepo.findAll());
    }

    public Boolean checkCredentials(String email, String password){
        User user = userRepo.findById(email).get();
        assert(user.authUsername(email, password));
         return user.authUsername(email, password);
    } 

    public Iterable<String> getUserFavorites(String email) {
        User user = userRepo.findById(email).get();
        return user.getFavorites();
    }

}