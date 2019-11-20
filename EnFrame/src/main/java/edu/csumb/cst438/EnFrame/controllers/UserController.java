package edu.csumb.cst438.EnFrame.controllers;

import edu.csumb.cst438.EnFrame.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import edu.csumb.cst438.EnFrame.models.User;
import org.springframework.web.bind.annotation.*;


@RestController
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
    public void checkCredentials(@RequestParam String email, String password)
    {
        userService.checkCredentials(email, password);
    }
    
}
