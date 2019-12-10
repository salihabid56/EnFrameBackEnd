package edu.csumb.cst438.EnFrame.controllers;

import edu.csumb.cst438.EnFrame.services.PhotoService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.csumb.cst438.EnFrame.models.Photo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @RequestMapping(method = RequestMethod.POST, value = "/photo/uploadPhoto")
    @ResponseBody
    public String uploadPhoto(@RequestParam("file") MultipartFile image) throws IOException {
        if(image == null){
            return "false";
        }
        return photoService.uploadPhoto(image);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/photo/uploadMetadata")
    @ResponseBody
    public Boolean uploadMetadata(@RequestParam String reference, @RequestParam ArrayList<String> tags, @RequestParam String userWhoUploaded) {
        return photoService.uploadMetadata(reference, tags, userWhoUploaded);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/photo/uploadPhotoAlt")
    @ResponseBody
    public String uploadPhotoAlt(@RequestParam String userWhoUploaded, @RequestParam List<String> tags) {
        return photoService.uploadPhotoAlt(userWhoUploaded, tags);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/photo/deletePhoto")
    @ResponseBody
    public Boolean deletePhoto(@RequestParam String reference) {
        return photoService.deletePhoto(reference);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/photo/getPhotosByTag")
    @ResponseBody
    public Iterable<Photo>getPhotosByTag(@RequestParam String tag)
    {
        return photoService.getPhotosByTag(tag);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/photo/getAllPhotos")
    @ResponseBody
    public Iterable<Photo> getAllPhotos()
    {
        return photoService.getAllPhotos();
    }

}
