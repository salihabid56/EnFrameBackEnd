package edu.csumb.cst438.EnFrame.controllers;

import edu.csumb.cst438.EnFrame.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.csumb.cst438.EnFrame.models.User;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/user/addUser")
    @ResponseBody
    public Boolean addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/getAllUsers")
    @ResponseBody
    public Iterable<User>getUsers()
    {
        return userService.getAllUsers();
    }

 
    @RequestMapping(method = RequestMethod.GET, value = "/user/checkCredentials")
    @ResponseBody
    public Boolean checkCredentials(@RequestParam String email, String password)

    {
        try {
            return(userService.checkCredentials(email, password));

        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/getUserFavorites")
    @ResponseBody
    public Iterable<String>getUserFavorites(@RequestParam String email)
    {
        return userService.getUserFavorites(email);
    }

}
