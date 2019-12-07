package edu.csumb.cst438.EnFrame.models;

import java.util.Set;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

public class Photo {

    @Id
   private String reference; //unique id
   private Set<String> tags;
   private String userWhoUploaded;
   private int favorites;

    // public Photo(){

    // }

    public Photo(Set<String> tags, String userWhoUploaded) {
        this.tags = tags;
        this.userWhoUploaded = userWhoUploaded;
        this.favorites = 0;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
         this.tags = tags;
     }

    public String getUserWhoUploaded(){
        return userWhoUploaded;
    }

    public void setUserWhoUploaded(String userWhoUploaded){
        this.userWhoUploaded = userWhoUploaded;
    }
 

    public int getFavorites(){
        return favorites;
    }

    public void setFavorites(int favorites){
        this.favorites = favorites;
    }

    public boolean hasTag(String tag) {
        for (String str : this.tags) {
            if (str.contains(tag)) {
                return true;
            }
        }
        return false;
    }
}