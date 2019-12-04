package edu.csumb.cst438.EnFrame.repository;

import edu.csumb.cst438.EnFrame.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
   
    public default Boolean insertIfExist(User u){
        if(findById(u.getEmail()).isPresent()){
            return false;
        }
        insert(u);
        return true;
    }
    
}



