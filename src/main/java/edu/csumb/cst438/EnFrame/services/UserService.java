package edu.csumb.cst438.EnFrame.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        user.setFavorites(new HashSet<String>());
        return userRepo.insertIfExist(user);
    }

    public User getUser (String email) {
        return userRepo.findById(email).get();
    }

    public Boolean deleteUser(String email) {
        userRepo.deleteById(email);
        return true;
    }

    public Boolean isAdmin(String email) {
        User user = userRepo.findById(email).get();
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

    public Boolean addFavorite(String email, String reference) {
        User user = userRepo.findById(email).get();
        Set<String> favorites = user.getFavorites();
        if (favorites == null) {
            favorites = new HashSet<String>();
        }
        favorites.add(reference);
        user.setFavorites(favorites);
        userRepo.save(user);
        return true;
    }

    public Boolean deleteFromAllFavorites(String reference) {
        List<User> allUsers = userRepo.findAll();
        for (User user : allUsers) {
            Set<String> favorites = user.getFavorites();
            if (favorites.contains(reference)) {
                favorites.remove(reference);
                userRepo.save(user);
            }
        }
        return true;
    }

}