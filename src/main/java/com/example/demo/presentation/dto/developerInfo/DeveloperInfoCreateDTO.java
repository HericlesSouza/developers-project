package com.example.demo.presentation.dto.developerInfo;

import com.example.demo.core.annotation.ValidEnum;
import com.example.demo.core.entity.OSEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperInfoCreateDTO {
        @NotNull(message = "developerSince is required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "developerSince must be in the format yyyy-MM-dd")
        private String developerSince;

        @NotNull(message = "preferredOS is required")
        @ValidEnum(enumClass = OSEnum.class, message = "Invalid value for preferredOS. Accepted values are: Windows, Linux, MacOS")
        private String preferredOS;
}