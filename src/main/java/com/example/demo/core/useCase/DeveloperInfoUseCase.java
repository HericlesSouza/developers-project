package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.DeveloperInfo;
import com.example.demo.core.entity.OSEnum;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.DeveloperRepository;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoCreateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperInfoUseCase {
    private final DeveloperRepository developerRepository;
    private final DeveloperUseCase developerUseCase;

    public Developer create(DeveloperInfoCreateDTO developerInfoCreateDTO, Long developerId) {
        Developer developer = this.developerUseCase.getById(developerId);

        LocalDate developerSince = LocalDate.parse(developerInfoCreateDTO.getDeveloperSince());
        OSEnum preferredOS = OSEnum.valueOf(developerInfoCreateDTO.getPreferredOS());

        DeveloperInfo developerInfo = new DeveloperInfo(developerSince, preferredOS, developer);

        developer.setDeveloperInfo(developerInfo);
        return this.developerRepository.save(developer);
    }

    public Developer update(DeveloperInfoUpdateDTO developerInfoUpdateDTO, Long developerId) {
        Developer developer = this.developerUseCase.getById(developerId);
        DeveloperInfo developerInfo = developer.getDeveloperInfo();

        if (developerInfo == null) {
            throw new ResourceNotFoundException("Developer with ID " + developerId + " doesn't have any associated information. Please create one.");
        }

        Optional.ofNullable(developerInfoUpdateDTO.getDeveloperSince()).ifPresent(
                developerSince -> developerInfo.setDeveloperSince(LocalDate.parse(developerSince))
        );

        Optional.ofNullable(developerInfoUpdateDTO.getPreferredOS()).ifPresent(
                os -> developerInfo.setPreferredOS(OSEnum.valueOf(os))
        );

        developer.setDeveloperInfo(developerInfo);
        return this.developerRepository.save(developer);
    }
}
