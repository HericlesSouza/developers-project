package com.example.demo.presentation.mapper;

import com.example.demo.core.entity.Project;
import com.example.demo.presentation.dto.project.*;
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

    public ProjectWithTechnologiesDTO toProjectWithTechnologiesDTO(Project project) {
        return modelMapper.map(project, ProjectWithTechnologiesDTO.class);
    }

    public <T extends ProjectDateInfo> Project toProjectEntity(T dataDTO) {
        Project project = modelMapper.map(dataDTO, Project.class);

        if (dataDTO instanceof ProjectCreateDTO || dataDTO instanceof ProjectUpdateDTO) {
            Optional.ofNullable(dataDTO.getStartDate())
                    .ifPresent(date -> project.setStartDate(LocalDate.parse(date)));

            Optional.ofNullable(dataDTO.getEndDate())
                    .ifPresent(date -> project.setEndDate(LocalDate.parse(date)));
        }

        return project;
    }
}