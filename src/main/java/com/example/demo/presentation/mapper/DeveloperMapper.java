package com.example.demo.presentation.mapper;

import com.example.demo.core.entity.Developer;
import com.example.demo.presentation.dto.developer.DeveloperDTO;
import com.example.demo.presentation.dto.developer.DeveloperDTOWithProjects;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeveloperMapper {
    private final ModelMapper modelMapper;

    public DeveloperDTO toDeveloperDTO(Developer developer) {
        return modelMapper.map(developer, DeveloperDTO.class);
    }

    public DeveloperDTOWithProjects toDeveloperDTOWithProjects(Developer developer) {
        return modelMapper.map(developer, DeveloperDTOWithProjects.class);
    }

    public <T> Developer toDeveloperEntity(T developerDTO) {
        return modelMapper.map(developerDTO, Developer.class);
    }
}
