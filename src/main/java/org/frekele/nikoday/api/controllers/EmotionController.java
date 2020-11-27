package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Emotion;
import org.frekele.nikoday.api.services.EmotionService;
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
public class EmotionController implements BaseController<Emotion, String> {

    private static final long serialVersionUID = -6857439120360273886L;

    private final EmotionService emotionService;

    @GetMapping("/emotions")
    public ResponseEntity<List<Emotion>> findAll() {
        List<Emotion> result = this.emotionService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-emotion")
    @ResponseBody
    public Page<Emotion> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<Emotion> page = this.emotionService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-emotion")
    @ResponseBody
    public Page<Emotion> findPageable(@RequestBody Emotion entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<Emotion> page = this.emotionService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-emotion")
    @ResponseBody
    public ResponseEntity<List<Emotion>> search(@RequestBody Emotion entity) {
        Example example = Example.of(entity);

        List<Emotion> result = this.emotionService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/emotion/{id}")
    @ResponseBody
    public ResponseEntity<Emotion> findById(@PathVariable @NotBlank String id) {
        Emotion entity = this.emotionService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/emotion")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<Emotion> create(@RequestBody @Valid Emotion entity) {
        Emotion savedEntity = this.emotionService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/emotion/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<Emotion> update(@RequestBody @Valid Emotion entity, @PathVariable @NotBlank String id) {
        Emotion updatedEntity = this.emotionService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/emotion/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        this.emotionService.delete(id);
    }

    @GetMapping("/count-emotion")
    public Long count() {
        return this.emotionService.count();
    }

    @PostMapping("/count-emotion")
    public Long count(@RequestBody Emotion entity) {
        Example example = Example.of(entity);
        return this.emotionService.count(example);
    }

}