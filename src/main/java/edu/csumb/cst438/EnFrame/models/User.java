package edu.csumb.cst438.EnFrame.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

public class User {

    @Id
   private String email; //unique id
   private String password;
   private String firstName;
   private String lastName;
   private Set<String> favorites;
   private boolean isAdmin;

    public User(){

    }

    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favorites = new HashSet<>();
        this.isAdmin = false;
    }

    @ApiModelProperty(required = false, hidden = true)
     HashMap<String, Integer> cart = new HashMap<>();

     public String getEmail(){
         return email;
     }
     
     public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
         this.password = password;
     }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
 

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Set<String> getFavorites() {
        return this.favorites;
    }

    public void setFavorites(Set<String> favorites) {
        this.favorites = favorites;
    }

    @ApiModelProperty(required = false, hidden = true)
    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void addToFavorites(String photoReference) {
        this.favorites.add(photoReference);
    }


    public Boolean authUsername(String email, String password)
    {
        if (this.email.equals(email) && this.password.equals(password))
        {
            System.out.println("Login Successful");
        }
        else{
            System.out.println("Login Failed. Bad Credentials");
        }
        return this.email.equals(email) && this.password.equals(password);
    }
}