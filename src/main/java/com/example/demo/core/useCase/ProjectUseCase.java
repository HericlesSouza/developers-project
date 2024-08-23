package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.Project;
import com.example.demo.core.entity.Technology;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.ProjectRepository;
import com.example.demo.core.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProjectUseCase {
    private final ProjectRepository repository;
    private final TechnologyRepository technologyRepository;
    private final DeveloperUseCase developerUseCase;
    private final ModelMapper modelMapper;

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

    public Project getById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
    }

    public List<Project> getAll() {
        return this.repository.findAll();
    }

    public Project update(Project data, Long id) {
        Project project = this.getById(id);

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(data, project);

        return this.repository.save(project);
    }

    public void delete(Long id) {
        Project project = this.getById(id);
        this.repository.delete(project);
    }

    public Project createTechnologyToProject(Long id, String technologyName) {
        Project project = this.getById(id);

        Technology technology = this.technologyRepository.findByName(technologyName)
                .orElseThrow(() -> new ResourceNotFoundException("Technology with name " + technologyName + " not found"));

        Set<Technology> technologies = project.getTechnologies();
        technologies.add(technology);
        project.setTechnologies(technologies);

        return this.repository.save(project);
    }

    public void deleteTechnologyFromProject(Long id, String technologyName) {
        Project project = this.getById(id);

        Set<Technology> technologies = project.getTechnologies();

        Technology technologyToDelete = technologies.stream()
                .filter(technology -> technology.getName().equalsIgnoreCase(technologyName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Technology with name " + technologyName + " not found"));

        technologies.remove(technologyToDelete);
        project.setTechnologies(technologies);
        this.repository.save(project);
    }
}