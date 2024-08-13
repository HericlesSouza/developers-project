package com.example.demo.presentation.dto.project;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDTO {
    @Size(max = 50, message = "the size must be less than 50 characters")
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @Size(max = 20, message = "the size must be less than 20 characters")
    @NotBlank(message = "estimatedTime is required")
    private String estimatedTime;

    @Size(max = 150, message = "the size must be less than 150 characters")
    @NotBlank(message = "repository is required")
    private String repository;

    @NotNull(message = "startDate is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "startDate must be in the format yyyy-MM-dd")
    private String startDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "endDate must be in the format yyyy-MM-dd")
    private String endDate;

    @NotNull(message = "developerId is required")
    @Positive(message = "developerId must be positive number")
    private Long developerId;
}
