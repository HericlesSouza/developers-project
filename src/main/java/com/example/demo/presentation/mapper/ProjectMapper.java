package com.example.demo.presentation.mapper;

import com.example.demo.core.entity.Project;
import com.example.demo.presentation.dto.project.ProjectCreateDTO;
import com.example.demo.presentation.dto.project.ProjectDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProjectMapper {
    private final ModelMapper modelMapper;

    public ProjectDTO toProjectDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public <T> Project toProjectEntity(T dataDTO) {
        Project project = modelMapper.map(dataDTO, Project.class);

        if (dataDTO instanceof ProjectCreateDTO projectCreateDTO) {
            project.setStartDate(LocalDate.parse(projectCreateDTO.getStartDate()));

            Optional.ofNullable(projectCreateDTO.getEndDate()).ifPresent(
                    date -> project.setEndDate(LocalDate.parse(date))
            );
        }

        return project;
    }
}