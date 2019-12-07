package edu.csumb.cst438.EnFrame.services;

import java.util.List;
import java.util.Optional;

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

    public Boolean deleteUser(String email) {
        userRepo.deleteById(email);
        return true;
    }

    public Boolean isAdmin(String email) {
        Optional<User> optionalUser =  userRepo.findById(email);
        User user = optionalUser.get();
        return user.getIsAdmin();
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