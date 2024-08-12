package com.example.demo.presentation.controller;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.useCase.DeveloperInfoUseCase;
import com.example.demo.core.useCase.DeveloperUseCase;
import com.example.demo.presentation.dto.developer.DeveloperCreateDTO;
import com.example.demo.presentation.dto.developer.DeveloperUpdateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoCreateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {
    private final DeveloperUseCase developerUseCase;
    private final DeveloperInfoUseCase developerInfoUseCase;

    @PostMapping
    public ResponseEntity<Developer> create(@RequestBody @Valid DeveloperCreateDTO data) {
        Developer newDeveloper = this.developerUseCase.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDeveloper);
    }

    @PostMapping("/{id}/infos")
    public ResponseEntity<Developer> createDeveloperInfo(
            @RequestBody @Valid DeveloperInfoCreateDTO data,
            @PathVariable Long id
    ) {
        Developer newDeveloperInfo = this.developerInfoUseCase.create(data, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDeveloperInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getById(@PathVariable Long id) {
        Developer developer = this.developerUseCase.getById(id);
        return ResponseEntity.ok().body(developer);
    }

    @GetMapping
    public ResponseEntity<List<Developer>> getAll() {
        List<Developer> developers = this.developerUseCase.getAll();
        return ResponseEntity.ok().body(developers);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Developer> update(
            @PathVariable Long id,
            @Valid @RequestBody DeveloperUpdateDTO data
    ) {
        Developer developer = this.developerUseCase.update(id, data);
        return ResponseEntity.ok().body(developer);
    }

    @PatchMapping("/{id}/infos")
    public ResponseEntity<Developer> updateDeveloperInfo(
            @RequestBody @Valid DeveloperInfoUpdateDTO data,
            @PathVariable Long id
    ) {
        Developer developer = this.developerInfoUseCase.update(data, id);
        return ResponseEntity.ok().body(developer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        this.developerUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
