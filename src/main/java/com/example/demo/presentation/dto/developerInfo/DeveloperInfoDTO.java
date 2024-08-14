package com.example.demo.presentation.dto.developerInfo;

import com.example.demo.core.entity.OSEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperInfoDTO {
    private Long id;
    private LocalDate developerSince;
    private OSEnum preferredOS;
}
