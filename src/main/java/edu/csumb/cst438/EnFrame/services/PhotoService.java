package edu.csumb.cst438.EnFrame.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.csumb.cst438.EnFrame.models.Photo;
import edu.csumb.cst438.EnFrame.repository.PhotoRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepo;
    Photo photo;

    
    
    // public void uploadImage(String objectKey, File image){
        
    // }
    // public void deleteImage(String objectKey){
    //     s3client.deleteObject("test-bucket438", objectKey);
    // }
    // */
    public Boolean uploadPhoto(MultipartFile image) throws SdkClientException, AmazonServiceException {
        //Do the stuff to upload photo on Amazon Bucket
        //objectKey is what we will name the file
        //File image is the image 
        // s3client.putObject("test-bucket438", photo.getReference(), image.getInputStream(), image.;
        AWSCredentials credentials = new BasicAWSCredentials("AKIAJZXSN226UE22E4IA",
                "SJhX0wud1FpY54e4KrX3wMsNrIcqAwDC3cypLGyn");
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_1).build();
        try {
            File convFile = new File(image.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
            s3client.putObject("test-bucket438", "pizza.jpg", convFile);
        } catch (IOException e){
            
        }

        // return photoRepo.insertIfExist(photo);
        return true;
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