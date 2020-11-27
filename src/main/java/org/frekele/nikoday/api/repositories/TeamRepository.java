package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.Team;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String>, BaseRepository<Team, String> {

}
