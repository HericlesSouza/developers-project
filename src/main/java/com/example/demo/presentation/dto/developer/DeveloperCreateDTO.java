package com.example.demo.presentation.dto.developer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperCreateDTO {
        @Size(max = 50, message = "the size must be less than 50 characters")
        @NotBlank(message = "name is required")
        private String name;

        @Email(message = "must be an email")
        @NotBlank(message = "email is required")
        @Size(max = 50, message = "the size must be less than 50 characters")
        private String email;
}
