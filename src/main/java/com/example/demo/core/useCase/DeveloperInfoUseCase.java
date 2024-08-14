package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.entity.DeveloperInfo;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperInfoUseCase {
    private final DeveloperRepository developerRepository;
    private final DeveloperUseCase developerUseCase;

    public Developer create(DeveloperInfo data, Long developerId) {
        Developer developer = this.developerUseCase.getById(developerId);

        DeveloperInfo developerInfo = new DeveloperInfo(data.getDeveloperSince(), data.getPreferredOS(), developer);

        developer.setDeveloperInfo(developerInfo);
        return this.developerRepository.save(developer);
    }

    public Developer update(DeveloperInfo data, Long developerId) {
        Developer developer = this.developerUseCase.getById(developerId);
        DeveloperInfo developerInfo = developer.getDeveloperInfo();

        if (developerInfo == null) {
            throw new ResourceNotFoundException("Developer with ID " + developerId + " doesn't have any associated information. Please create one.");
        }

        Optional.ofNullable(data.getDeveloperSince()).ifPresent(developerInfo::setDeveloperSince);

        Optional.ofNullable(data.getPreferredOS()).ifPresent(developerInfo::setPreferredOS);

        developer.setDeveloperInfo(developerInfo);
        return this.developerRepository.save(developer);
    }
}
