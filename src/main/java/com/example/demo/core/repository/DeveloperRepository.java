package com.example.demo.core.repository;

import com.example.demo.core.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    boolean existsByEmail(String email);
}
