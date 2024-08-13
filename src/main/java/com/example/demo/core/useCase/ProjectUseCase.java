package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.Project;
import com.example.demo.core.repository.ProjectRepository;
import com.example.demo.presentation.dto.project.ProjectCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectUseCase {
    private final ProjectRepository repository;
    private final DeveloperUseCase developerUseCase;

    public Project create(ProjectCreateDTO projectCreateDTO) {
        Developer developer = this.developerUseCase.getById(projectCreateDTO.getDeveloperId());

        Project project = new Project(
                projectCreateDTO.getName(),
                projectCreateDTO.getDescription(),
                projectCreateDTO.getEstimatedTime(),
                projectCreateDTO.getRepository(),
                LocalDate.parse(projectCreateDTO.getStartDate()),
                developer
        );

        Optional.ofNullable(projectCreateDTO.getEndDate()).ifPresent(
                date -> project.setEndDate(LocalDate.parse(date))
        );

        return this.repository.save(project);
    }
}
