package edu.csumb.cst438.EnFrame.repository;

import edu.csumb.cst438.EnFrame.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String>
{
    @Query (value = "{'id':?0}")
    Photo findByRepoId(String id);
    
    public default Boolean insertIfExist(Photo p){
        if(findById(p.getReference()).isPresent()){
            return false;
        }
        insert(p);
        return true;
    }

}



