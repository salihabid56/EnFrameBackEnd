package edu.csumb.cst438.EnFrame.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

     public Boolean deletePhoto(String reference){

        AWSCredentials credentials = new BasicAWSCredentials("AKIAJZXSN226UE22E4IA",
                "SJhX0wud1FpY54e4KrX3wMsNrIcqAwDC3cypLGyn");
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_1).build();

         s3client.deleteObject("test-bucket438", reference);
         photoRepo.deleteById(reference);
         return true;
     }
    public String uploadPhoto(MultipartFile image) throws SdkClientException, AmazonServiceException {
        //Do the stuff to upload photo on Amazon Bucket
        //objectKey is what we will name the file
        //File image is the image 
        // s3client.putObject("test-bucket438", photo.getReference(), image.getInputStream(), image.;

        //Make sure it is a correct photo file format

        String contentType = image.getContentType();
        if (contentType.length() < 5 || !contentType.substring(0,5).equals("image")) {
            return "false";
        }

        // //Make the photo Object to be put in DB

        HashSet<String> photoTags = new HashSet<>();

        Photo photo = new Photo(photoTags, "Error: Not Set Yet");
        photoRepo.save(photo);

        String photoReference = photo.getReference(); //Use this to make the bucket url

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
            //s3client.putObject("test-bucket438", "puppy", convFile);
            s3client.putObject("test-bucket438", photoReference, convFile); //Use this when you actually want to start uploading to bucket
        } catch (IOException e){
            return "false";
        }
       
        return photoReference;
    }

    public Boolean uploadMetadata(String reference, ArrayList<String> tags, String userWhoUploaded) {
        Photo photo = photoRepo.findById(reference).get();
        Set<String> photoTags = photo.getTags();

        for (String tag : tags) {
            photoTags.add(tag);
        }

        photo.setUserWhoUploaded(userWhoUploaded);
        photoRepo.save(photo);
        return true;
    }

    public String uploadPhotoAlt(String userWhoUpoaded, List<String> tags) {

        //Make the photo Object to be put in DB

        HashSet<String> photoTags = new HashSet<>();

        for (String str : tags) {
            photoTags.add(str.toLowerCase());
        }

        Photo photo = new Photo(photoTags, userWhoUpoaded);
        photoRepo.save(photo);

        String photoReference = photo.getReference(); //Use this to make the bucket url
       
        return photoReference;
    }

    public Iterable<Photo> getPhotosByTag(String tag) {
        List<Photo> allPhotos = photoRepo.findAll();
        ArrayList<Photo> matchedPhotos = new ArrayList<>();
        tag = tag.trim();
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