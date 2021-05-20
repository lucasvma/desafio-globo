package com.globo.challenge.repository;

import com.globo.challenge.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByDocument(@Param("document") String document);
    User findByDocumentOrName(@Param("document") String document, @Param("name") String name);
}
