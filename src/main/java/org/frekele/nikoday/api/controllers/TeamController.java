package org.frekele.nikoday.api.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Team;
import org.frekele.nikoday.api.services.TeamService;
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
@RequestMapping("/")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamController implements BaseController<Team, String> {

    private static final long serialVersionUID = 6664895076483510290L;

    private final TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> findAll() {
        List<Team> result = this.teamService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-team")
    @ResponseBody
    public Page<Team> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<Team> page = this.teamService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-team")
    @ResponseBody
    public Page<Team> findPageable(@RequestBody Team entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<Team> page = this.teamService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-team")
    @ResponseBody
    public ResponseEntity<List<Team>> search(@RequestBody Team entity) {
        Example example = Example.of(entity);

        List<Team> result = this.teamService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/team/{id}")
    @ResponseBody
    public ResponseEntity<Team> findById(@PathVariable @NotBlank String id) {
        Team entity = this.teamService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/team")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<Team> create(@RequestBody @Valid Team entity) {
        Team savedEntity = this.teamService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/team/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<Team> update(@RequestBody @Valid Team entity, @PathVariable @NotBlank String id) {
        Team updatedEntity = this.teamService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/team/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        this.teamService.delete(id);
    }

    @GetMapping("/count-team")
    public Long count() {
        return this.teamService.count();
    }

    @PostMapping("/count-team")
    public Long count(@RequestBody Team entity) {
        Example example = Example.of(entity);
        return this.teamService.count(example);
    }

}