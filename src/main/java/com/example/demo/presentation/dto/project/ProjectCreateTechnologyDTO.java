package com.example.demo.presentation.dto.project;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateTechnologyDTO {
    @Size(max = 30, message = "the size must be less than 30 characters")
    @NotNull
    private String name;
}
