package edu.csumb.cst438.EnFrame.controllers;

import edu.csumb.cst438.EnFrame.services.PhotoService;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;

import edu.csumb.cst438.EnFrame.models.Photo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @RequestMapping(method = RequestMethod.POST, value = "/photo/uploadPhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean uploadPhoto(HttpServletRequest request, @RequestPart(value = "Myfile") MultipartFile image) throws IOException {
        if(image == null){
            return false;
        }
        return photoService.uploadPhoto(image);

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
