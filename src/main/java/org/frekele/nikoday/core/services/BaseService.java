package org.frekele.nikoday.core.services;

import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends PersistentBaseEntity<ID>, ID> implements Serializable {

    private static final long serialVersionUID = 6068341250259997243L;

    protected abstract MongoRepository<T, ID> getRepository();

    public List<T> findAll() {
        List<T> result = this.getRepository().findAll();
        return result;
    }

    public List<T> findAll(Example<T> example) {
        List<T> result = this.getRepository().findAll(example);
        return result;
    }

    public List<T> findAll(Sort sort) {
        List<T> result = this.getRepository().findAll(sort);
        return result;
    }

    public Page<T> findAll(Pageable pageable) {
        Page<T> result = this.getRepository().findAll(pageable);
        return result;
    }

    public List<T> findAll(Example<T> example, Sort sort) {
        List<T> result = this.getRepository().findAll(example, sort);
        return result;
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        Page<T> result = this.getRepository().findAll(example, pageable);
        return result;
    }

    public List<T> findAllByIds(List<ID> ids) {
        List<T> result = new ArrayList<>();
        Iterable<T> iterable = this.getRepository().findAllById(ids);
        iterable.forEach(result::add);
        return result;
    }

    public T findById(ID id) {
        Optional<T> entity = this.getRepository().findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("id-" + id);
        }
        return entity.get();
    }

    public boolean exists(Example<T> example) {
        boolean result = this.getRepository().exists(example);
        return result;
    }

    public boolean exists(ID id) {
        boolean result = this.getRepository().existsById(id);
        return result;
    }

    public long count(Example<T> example) {
        long result = this.getRepository().count(example);
        return result;
    }

    public long count() {
        long result = this.getRepository().count();
        return result;
    }

    public T create(T entity) {
        T savedEntity = this.getRepository().save(entity);
        return savedEntity;
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        //        .buildAndExpand(savedEntity.getId()).toUri();
        //return ResponseEntity.created(location).build();
    }

    public T update(T entity, ID id) {
        Optional<T> entityFind = this.getRepository().findById(id);
        if (entityFind.isEmpty()) {
            return null;
        }
        entity.setId(id);
        entity.setCreatedDateTime(entityFind.get().getCreatedDateTime());
        T updatedEntity = this.getRepository().save(entity);
        return updatedEntity;
    }

    public void delete(ID id) {
        this.getRepository().deleteById(id);
    }

    public void deleteAll() {
        this.getRepository().deleteAll();
    }

}
