package com.rafaelsegalla.workshopspringbootmongodb.repository;

import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
