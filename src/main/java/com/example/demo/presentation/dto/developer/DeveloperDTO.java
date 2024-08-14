package com.example.demo.presentation.dto.developer;

import com.example.demo.presentation.dto.developerInfo.DeveloperInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    private Long id;
    private String name;
    private String email;
    private DeveloperInfoDTO developerInfo;
}
