package com.example.demo.presentation.dto.project;

import com.example.demo.core.annotation.OptionalNotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO implements ProjectDateInfo {
    @Size(max = 50, message = "the size must be less than 50 characters")
    @OptionalNotBlank(message = "name is required")
    private String name;

    @OptionalNotBlank(message = "description is required")
    private String description;

    @Size(max = 20, message = "the size must be less than 20 characters")
    @OptionalNotBlank(message = "estimatedTime is required")
    private String estimatedTime;

    @Size(max = 150, message = "the size must be less than 150 characters")
    @OptionalNotBlank(message = "repository is required")
    private String repository;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "startDate must be in the format yyyy-MM-dd")
    private String startDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "endDate must be in the format yyyy-MM-dd")
    private String endDate;
}
