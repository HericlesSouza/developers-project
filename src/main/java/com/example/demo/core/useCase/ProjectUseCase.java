package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.Project;
import com.example.demo.core.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectUseCase {
    private final ProjectRepository repository;
    private final DeveloperUseCase developerUseCase;

    public Project create(Project data) {
        Developer developer = this.developerUseCase.getById(data.getDeveloper().getId());

        Project project = new Project(
                data.getName(),
                data.getDescription(),
                data.getEstimatedTime(),
                data.getRepository(),
                data.getStartDate(),
                data.getEndDate(),
                developer
        );

        return this.repository.save(project);
    }
}