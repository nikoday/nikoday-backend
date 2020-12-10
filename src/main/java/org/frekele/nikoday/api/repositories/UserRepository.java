package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, BaseRepository<User, String> {

    User findByEmailAndPassword(String email, String password);

}
