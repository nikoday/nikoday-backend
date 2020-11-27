package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.api.services.UserService;
import org.frekele.nikoday.core.controllers.BaseController;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnDelete;
import org.frekele.nikoday.core.validations.OnUpdate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/nikoday")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserController implements BaseController<User, String> {

    private static final long serialVersionUID = 5796375769846326134L;

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> result = this.userService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-user")
    @ResponseBody
    public Page<User> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<User> page = this.userService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-user")
    @ResponseBody
    public Page<User> findPageable(@RequestBody User entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<User> page = this.userService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-user")
    @ResponseBody
    public ResponseEntity<List<User>> search(@RequestBody User entity) {
        Example example = Example.of(entity);

        List<User> result = this.userService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<User> findById(@PathVariable @NotBlank String id) {
        User entity = this.userService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/user")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<User> create(@RequestBody @Valid User entity) {
        User savedEntity = this.userService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<User> update(@RequestBody @Valid User entity, @PathVariable @NotBlank String id) {
        User updatedEntity = this.userService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/user/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        this.userService.delete(id);
    }

    @GetMapping("/count-user")
    public Long count() {
        return this.userService.count();
    }

    @PostMapping("/count-user")
    public Long count(@RequestBody User entity) {
        Example example = Example.of(entity);
        return this.userService.count(example);
    }

}