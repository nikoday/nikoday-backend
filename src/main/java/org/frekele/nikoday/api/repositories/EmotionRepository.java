package org.frekele.nikoday.api.repositories;

import org.frekele.nikoday.api.entities.Emotion;
import org.frekele.nikoday.core.repositories.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends MongoRepository<Emotion, String>, BaseRepository<Emotion, String> {

}
