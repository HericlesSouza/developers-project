package com.example.demo.presentation.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String estimatedTime;
    private String repository;
    private LocalDate startDate;
    private LocalDate endDate;
}

