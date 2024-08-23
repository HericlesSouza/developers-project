package com.example.demo.presentation.mapper;

import com.example.demo.core.entity.DeveloperInfo;
import com.example.demo.core.entity.OSEnum;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoCreateDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoDTO;
import com.example.demo.presentation.dto.developerInfo.DeveloperInfoUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DeveloperInfoMapper {
    private final ModelMapper modelMapper;

    public DeveloperInfoDTO toDeveloperInfoDTO(DeveloperInfo developerInfo) {
        return modelMapper.map(developerInfo, DeveloperInfoDTO.class);
    }

    public <T> DeveloperInfo toDeveloperInfoEntity(T dataDTO) {
        DeveloperInfo developerInfo = modelMapper.map(dataDTO, DeveloperInfo.class);

        if (dataDTO instanceof DeveloperInfoCreateDTO developerInfoCreateDTO) {

            if (developerInfoCreateDTO.getDeveloperSince() != null) {
                developerInfo.setDeveloperSince(LocalDate.parse(developerInfoCreateDTO.getDeveloperSince()));
            }

            if (developerInfoCreateDTO.getPreferredOS() != null) {
                developerInfo.setPreferredOS(OSEnum.valueOf(developerInfoCreateDTO.getPreferredOS()));
            }
        }

        if (dataDTO instanceof DeveloperInfoUpdateDTO developerInfoUpdateDTO) {

            if (developerInfoUpdateDTO.getDeveloperSince() != null) {
                developerInfo.setDeveloperSince(LocalDate.parse(developerInfoUpdateDTO.getDeveloperSince()));
            }

            if (developerInfoUpdateDTO.getPreferredOS() != null) {
                developerInfo.setPreferredOS(OSEnum.valueOf(developerInfoUpdateDTO.getPreferredOS()));
            }
        }

        return developerInfo;
    }
}

