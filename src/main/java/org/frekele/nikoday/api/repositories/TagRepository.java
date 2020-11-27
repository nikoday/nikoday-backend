package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.Tag;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, String>, BaseRepository<Tag, String> {

}
