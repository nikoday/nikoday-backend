package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.Registry;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends MongoRepository<Registry, String>, BaseRepository<Registry, String> {

}
