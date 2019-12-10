package edu.csumb.cst438.EnFrame.controllers;

import edu.csumb.cst438.EnFrame.services.PhotoService;
import java.io.File;
import java.io.IOException;
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
    public Boolean uploadPhoto(@RequestPart(value = "file") MultipartFile image, @RequestParam String userWhoUploaded, @RequestParam List<String> tags) throws IOException {
        if(image == null){
            return false;
        }
        return photoService.uploadPhoto(image, userWhoUploaded, tags);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/photo/uploadPhotoAlt")
    @ResponseBody
    public String uploadPhotoAlt(@RequestParam String userWhoUploaded, @RequestParam List<String> tags) {
        return photoService.uploadPhotoAlt(userWhoUploaded, tags);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/photo/uploadPhotoAltToo")
    @ResponseBody
    public Boolean uploadPhotoAltToo(@RequestParam String photoUrl) {
        return photoService.uploadPhotoAltToo(photoUrl);

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
