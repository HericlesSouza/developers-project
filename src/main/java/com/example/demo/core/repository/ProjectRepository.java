package com.example.demo.core.repository;

import com.example.demo.core.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Query("SELECT p FROM Project p " +
//            "JOIN FETCH p.developer d " +
//            "LEFT JOIN FETCH p.technologies t")
//    List<Project> findAllWithDetails();
}
