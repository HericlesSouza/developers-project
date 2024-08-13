package com.example.demo.core.repository;

import com.example.demo.core.entity.DeveloperInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperInfoRepository extends JpaRepository<DeveloperInfo, Long> {
}
