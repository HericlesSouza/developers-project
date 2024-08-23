package com.example.demo.presentation.dto.project;

import com.example.demo.presentation.dto.technology.TechnologyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWithTechnologiesDTO {
    private Long id;
    private String name;
    private String description;
    private String estimatedTime;
    private String repository;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<TechnologyDTO> technologies;
}
