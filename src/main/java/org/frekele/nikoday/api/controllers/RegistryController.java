package org.frekele.nikoday.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Registry;
import org.frekele.nikoday.api.services.RegistryService;
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
public class RegistryController implements BaseController<Registry, String> {

    private static final long serialVersionUID = -2935407158858517763L;

    private final RegistryService registryService;

    @GetMapping("/registries")
    public ResponseEntity<List<Registry>> findAll() {
        List<Registry> result = registryService.findAll();
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagination-registry")
    @ResponseBody
    public Page<Registry> findAllPageable(@PageableDefault(page = 0, size = 50) Pageable pageable) {
        Page<Registry> page = registryService.findAll(pageable);
        return page;
    }

    @PostMapping("/pagination-registry")
    @ResponseBody
    public Page<Registry> findPageable(@RequestBody Registry entity, @PageableDefault(page = 0, size = 50) Pageable pageable) {
        Example example = Example.of(entity);
        Page<Registry> page = registryService.findAll(example, pageable);
        return page;
    }

    @PostMapping("/search-registry")
    @ResponseBody
    public ResponseEntity<List<Registry>> search(@RequestBody Registry entity) {
        Example example = Example.of(entity);

        List<Registry> result = registryService.findAll(example);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/registry/{id}")
    @ResponseBody
    public ResponseEntity<Registry> findById(@PathVariable @NotBlank String id) {
        Registry entity = registryService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/registry")
    @ResponseBody
    @Validated(OnCreate.class)
    public ResponseEntity<Registry> create(@RequestBody @Valid Registry entity) {
        Registry savedEntity = registryService.create(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/registry/{id}")
    @ResponseBody
    @Validated(OnUpdate.class)
    public ResponseEntity<Registry> update(@RequestBody @Valid Registry entity, @PathVariable @NotBlank String id) {
        Registry updatedEntity = registryService.update(entity, id);
        if (updatedEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/registry/{id}")
    @Validated(OnDelete.class)
    public void delete(@PathVariable @NotBlank String id) {
        registryService.delete(id);
    }

    @GetMapping("/count-registry")
    public Long count() {
        return registryService.count();
    }

    @PostMapping("/count-registry")
    public Long count(@RequestBody Registry entity) {
        Example example = Example.of(entity);
        return registryService.count(example);
    }

}