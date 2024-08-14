package com.example.demo.presentation.controller;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.DeveloperInfo;
import com.example.demo.core.useCase.DeveloperInfoUseCase;
import com.example.demo.core.useCase.DeveloperUseCase;
import com.example.demo.presentation.dto.developer.DeveloperCreateDTO;
import com.example.demo.presentation.dto.developer.DeveloperDTO;
import com.example.demo.presentation.dto.developer.DeveloperDTOWithProjects;
import com.example.demo.presentation.dto.developer.DeveloperUpdateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoCreateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoUpdateDTO;
import com.example.demo.presentation.mapper.DeveloperInfoMapper;
import com.example.demo.presentation.mapper.DeveloperMapper;
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
    private final DeveloperMapper developerMapper;
    private final DeveloperInfoMapper developerInfoMapper;

    @PostMapping
    public ResponseEntity<DeveloperDTO> create(@RequestBody @Valid DeveloperCreateDTO data) {
        Developer developer = this.developerMapper.toDeveloperEntity(data);
        Developer developerCreated = this.developerUseCase.create(developer);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(developerCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(developerDTO);
    }

    @PostMapping("/{id}/infos")
    public ResponseEntity<DeveloperDTO> createDeveloperInfo(
            @RequestBody @Valid DeveloperInfoCreateDTO data,
            @PathVariable Long id
    ) {
        DeveloperInfo developerInfo = this.developerInfoMapper.toDeveloperInfoEntity(data);
        Developer createdDeveloperInfo = this.developerInfoUseCase.create(developerInfo, id);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(createdDeveloperInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(developerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getById(@PathVariable Long id) {
        Developer developer = this.developerUseCase.getById(id);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(developer);
        return ResponseEntity.ok().body(developerDTO);
    }

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAll() {
        List<Developer> developers = this.developerUseCase.getAll();
        List<DeveloperDTO> developerDTOs = developers.stream()
                .map(developerMapper::toDeveloperDTO)
                .toList();
        return ResponseEntity.ok().body(developerDTOs);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<DeveloperDTOWithProjects> getProjects(@PathVariable Long id) {
        Developer developer = this.developerUseCase.getProjects(id);
        DeveloperDTOWithProjects developerDTOWithProjects = this.developerMapper.toDeveloperDTOWithProjects(developer);
        return ResponseEntity.ok().body(developerDTOWithProjects);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeveloperDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DeveloperUpdateDTO data
    ) {
        Developer developer = this.developerMapper.toDeveloperEntity(data);
        Developer developerCreated = this.developerUseCase.update(id, developer);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(developerCreated);
        return ResponseEntity.ok().body(developerDTO);
    }

    @PatchMapping("/{id}/infos")
    public ResponseEntity<DeveloperDTO> updateDeveloperInfo(
            @RequestBody @Valid DeveloperInfoUpdateDTO data,
            @PathVariable Long id
    ) {
        DeveloperInfo developerInfo = this.developerInfoMapper.toDeveloperInfoEntity(data);
        Developer developer = this.developerInfoUseCase.update(developerInfo, id);
        DeveloperDTO developerDTO = this.developerMapper.toDeveloperDTO(developer);
        return ResponseEntity.ok().body(developerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        this.developerUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

