package com.example.demo.core.repository;

import com.example.demo.core.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByName(String name);
}
