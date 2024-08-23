package com.example.demo.presentation.controller;

import com.example.demo.core.entity.Project;
import com.example.demo.core.useCase.ProjectUseCase;
import com.example.demo.presentation.dto.project.*;
import com.example.demo.presentation.mapper.ProjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectUseCase projectUseCase;
    private final ProjectMapper projectMapper;

    @PostMapping()
    public ResponseEntity<ProjectDTO> create(@RequestBody @Valid ProjectCreateDTO projectCreateDTO) {
        Project project = this.projectMapper.toProjectEntity(projectCreateDTO);
        Project projectCreated = this.projectUseCase.create(project);
        ProjectDTO projectDTO = this.projectMapper.toProjectDTO(projectCreated);
        return ResponseEntity.ok().body(projectDTO);
    }

    @PostMapping("/{id}/technologies")
    public ResponseEntity<ProjectWithTechnologiesDTO> createTechnologyToProject(
            @RequestBody @Valid ProjectCreateTechnologyDTO projectCreateTechnologyDTO,
            @PathVariable Long id
    ) {
        Project project = this.projectUseCase.createTechnologyToProject(id, projectCreateTechnologyDTO.getName());
        ProjectWithTechnologiesDTO projectWithTechnologiesDTO = this.projectMapper.toProjectWithTechnologiesDTO(project);
        return ResponseEntity.ok().body(projectWithTechnologiesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id) {
        Project project = this.projectUseCase.getById(id);
        ProjectDTO projectDTO = this.projectMapper.toProjectDTO(project);
        return ResponseEntity.ok().body(projectDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectWithTechnologiesDTO>> getAll() {
        List<Project> projects = this.projectUseCase.getAll();
        List<ProjectWithTechnologiesDTO> projectsWithTechnologiesDTO = projects.stream()
                .map(this.projectMapper::toProjectWithTechnologiesDTO)
                .toList();
        return ResponseEntity.ok().body(projectsWithTechnologiesDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(
            @RequestBody @Valid ProjectUpdateDTO projectUpdateDTO,
            @PathVariable Long id
    ) {
        Project project = this.projectMapper.toProjectEntity(projectUpdateDTO);
        Project projectUpdated = this.projectUseCase.update(project, id);
        ProjectDTO projectDTO = this.projectMapper.toProjectDTO(projectUpdated);
        return ResponseEntity.ok().body(projectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.projectUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/technologies/{technologyName}")
    public ResponseEntity<Void> deleteTechnologyFromProject(
            @PathVariable Long id,
            @PathVariable String technologyName
    ) {
        this.projectUseCase.deleteTechnologyFromProject(id, technologyName);
        return ResponseEntity.noContent().build();
    }
}