package com.example.demo.presentation.dto.developer;

import com.example.demo.core.annotation.OptionalNotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperUpdateDTO {
        @Size(max = 50, message = "the size must be less than 50 characters")
        @OptionalNotBlank(message = "name must not be blank")
        private String name;

        @Size(max = 50, message = "the size must be less than 50 characters")
        @OptionalNotBlank(message = "email must not be blank")
        @Email(message = "must be an email")
        private String email;
}
