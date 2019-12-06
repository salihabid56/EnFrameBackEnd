package edu.csumb.cst438.EnFrame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csumb.cst438.EnFrame.models.Photo;
import edu.csumb.cst438.EnFrame.repository.PhotoRepository;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepo;
    Photo photo;

    public Boolean uploadPhoto(Photo photo) {

        //Do the stuff to upload photo on Amazon Bucket

        return photoRepo.insertIfExist(photo);
    }

    public Iterable<Photo> getPhotosByTag(String tag) {
        List<Photo> allPhotos = photoRepo.findAll();
        ArrayList<Photo> matchedPhotos = new ArrayList<>();
        for (Photo p : allPhotos) {
            if (p.hasTag(tag)) {
                matchedPhotos.add(p);
            }
        } 
        return matchedPhotos;
    }

    public Iterable<Photo> getAllPhotos() {
        return (photoRepo.findAll());
    }

}