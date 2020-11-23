package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.api.services.UserService;
import org.frekele.nikoday.core.controllers.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/nikoday")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserController implements BaseController<User, String> {

    private static final long serialVersionUID = 7292877594548006929L;

    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.create(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
        User updatedUser = userService.update(user, id);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

}

