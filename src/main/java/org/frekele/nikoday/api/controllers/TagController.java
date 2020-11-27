package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Tag;
import org.frekele.nikoday.api.services.TagService;
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
public class TagController implements BaseController<Tag, String> {

    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> result = tagService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-tag")
    @ResponseBody
    public Page<Tag> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<Tag> page = tagService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-tag")
    @ResponseBody
    public Page<Tag> findPageable(@RequestBody Tag entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<Tag> page = tagService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-tag")
    @ResponseBody
    public ResponseEntity<List<Tag>> search(@RequestBody Tag entity) {
        Example example = Example.of(entity);

        List<Tag> result = tagService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tag/{id}")
    @ResponseBody
    public ResponseEntity<Tag> findById(@PathVariable @NotBlank String id) {
        Tag entity = tagService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/tag")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<Tag> create(@RequestBody @Valid Tag entity) {
        Tag savedEntity = tagService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/tag/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<Tag> update(@RequestBody @Valid Tag entity, @PathVariable @NotBlank String id) {
        Tag updatedEntity = tagService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/tag/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        tagService.delete(id);
    }

    @GetMapping("/count-tag")
    public Long count() {
        return tagService.count();
    }

    @PostMapping("/count-tag")
    public Long count(@RequestBody Tag entity) {
        Example example = Example.of(entity);
        return tagService.count(example);
    }

}