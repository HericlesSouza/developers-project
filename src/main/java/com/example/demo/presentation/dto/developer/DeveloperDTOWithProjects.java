package com.example.demo.presentation.dto.developer;

import com.example.demo.presentation.dto.developerInfo.DeveloperInfoDTO;
import com.example.demo.presentation.dto.project.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTOWithProjects {
    private Long id;
    private String name;
    private String email;
    private DeveloperInfoDTO developerInfo;
    private List<ProjectDTO> projects;
}
