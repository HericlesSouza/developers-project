package com.example.demo.core.useCase;

import com.example.demo.core.entity.Developer;
import com.example.demo.core.exception.EmailAlreadyExistsException;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.repository.DeveloperRepository;
import com.example.demo.presentation.dto.developer.DeveloperCreateDTO;
import com.example.demo.presentation.dto.developer.DeveloperUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DeveloperUseCase {
    private final DeveloperRepository repository;
    private final ModelMapper modelMapper;

    public Developer create(DeveloperCreateDTO developerCreateDTO) {
        this.validateEmailDoesNotExist(developerCreateDTO.getEmail());

        Developer developer = new Developer(developerCreateDTO);
        this.repository.save(developer);
        return developer;
    }

    public Developer getById(Long id) {
        return this.repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Developer with id " + id + " not found"));
    }

    public List<Developer> getAll() {
        return this.repository.findAll();
    }

    public Developer update(Long id, DeveloperUpdateDTO developerUpdateDTO) {
        Developer existingDeveloper = this.repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Developer with id " + id + " not found"));

        if (developerUpdateDTO.getEmail() != null && !developerUpdateDTO.getEmail().isEmpty()) {
            this.validateEmailDoesNotExist(developerUpdateDTO.getEmail());
        }

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(developerUpdateDTO, existingDeveloper);

        this.repository.save(existingDeveloper);

        return existingDeveloper;
    }

    public void delete(Long id) {
        this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Developer with id " + id + " not found"));
        this.repository.deleteById(id);
    }

    private void validateEmailDoesNotExist(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("The email " + email + " already exists.");
        }
    }
}