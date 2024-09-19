package com.example.demo.core.repository;

import com.example.demo.core.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    @Query("SELECT d FROM Developer d WHERE d.email = :email")
    Optional<Developer> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT d FROM Developer d LEFT JOIN FETCH d.projects WHERE d.id =:id")
    Optional<Developer> findDeveloperWithProjects(Long id);
}