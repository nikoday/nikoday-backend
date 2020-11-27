package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.api.services.TeamUserService;
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
public class TeamUserController implements BaseController<TeamUser, String> {

    private static final long serialVersionUID = 8246588474104304957L;

    private final TeamUserService teamUserService;

    @GetMapping("/team-users")
    public ResponseEntity<List<TeamUser>> findAll() {
        List<TeamUser> result = this.teamUserService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-team-user")
    @ResponseBody
    public Page<TeamUser> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<TeamUser> page = this.teamUserService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-team-user")
    @ResponseBody
    public Page<TeamUser> findPageable(@RequestBody TeamUser entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<TeamUser> page = this.teamUserService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-team-user")
    @ResponseBody
    public ResponseEntity<List<TeamUser>> search(@RequestBody TeamUser entity) {
        Example example = Example.of(entity);

        List<TeamUser> result = this.teamUserService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/team-user/{id}")
    @ResponseBody
    public ResponseEntity<TeamUser> findById(@PathVariable @NotBlank String id) {
        TeamUser entity = this.teamUserService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/team-user")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<TeamUser> create(@RequestBody @Valid TeamUser entity) {
        TeamUser savedEntity = this.teamUserService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/team-user/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<TeamUser> update(@RequestBody @Valid TeamUser entity, @PathVariable @NotBlank String id) {
        TeamUser updatedEntity = this.teamUserService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/team-user/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        this.teamUserService.delete(id);
    }

    @GetMapping("/count-team-user")
    public Long count() {
        return this.teamUserService.count();
    }

    @PostMapping("/count-team-user")
    public Long count(@RequestBody TeamUser entity) {
        Example example = Example.of(entity);
        return this.teamUserService.count(example);
    }

}