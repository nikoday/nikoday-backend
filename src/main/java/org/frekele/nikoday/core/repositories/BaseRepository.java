package org.frekele.nikoday.core.repositories;

import org.frekele.nikoday.core.entities.PersistentBaseEntity;

import java.io.Serializable;

public interface BaseRepository<T extends PersistentBaseEntity<ID>, ID> extends Serializable {

}
