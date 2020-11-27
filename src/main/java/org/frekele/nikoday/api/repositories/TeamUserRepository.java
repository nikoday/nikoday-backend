package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserRepository extends MongoRepository<TeamUser, String>, BaseRepository<TeamUser, String> {

}
