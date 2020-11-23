package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.api.repositories.UserRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService implements BaseService<User, String> {

    private static final long serialVersionUID = 8373477910473452578L;

    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> student = userRepository.findById(id);
        if (!student.isPresent()) {
            throw new EntityNotFoundException("id-" + id);
        }
        return student.get();
    }

    public User create(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        //        .buildAndExpand(savedUser.getId()).toUri();
        //return ResponseEntity.created(location).build();
    }

    public User update(User user, String id) {
        Optional<User> studentOptional = userRepository.findById(id);
        if (!studentOptional.isPresent()) {
            return null;
        }
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
