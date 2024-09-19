package com.example.demo.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"developer", "technologies"})
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max =  50)
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Size(max = 20)
    @Column(name = "estimated_time")
    private String estimatedTime;

    @NotBlank
    @Size(max = 150)
    private String repository;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_technologies",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies;

    public Project(
            String name,
            String description,
            String estimatedTime,
            String repository,
            LocalDate startDate,
            Developer developer
    ) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.repository = repository;
        this.startDate = startDate;
        this.developer = developer;
    }

    public Project(
            String name,
            String description,
            String estimatedTime,
            String repository,
            LocalDate startDate,
            LocalDate endDate,
            Developer developer
    ) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.repository = repository;
        this.startDate = startDate;
        this.endDate = endDate;
        this.developer = developer;
    }
}
