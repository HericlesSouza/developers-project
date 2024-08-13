package com.example.demo.presentation.controller;


import com.example.demo.core.entity.Project;
import com.example.demo.core.useCase.ProjectUseCase;
import com.example.demo.presentation.dto.project.ProjectCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectUseCase projectUseCase;

    @PostMapping()
    public ResponseEntity<Project> create(@RequestBody @Valid ProjectCreateDTO projectCreateDTO) {
        Project project = this.projectUseCase.create(projectCreateDTO);
        return ResponseEntity.ok().body(project);
    }
}
