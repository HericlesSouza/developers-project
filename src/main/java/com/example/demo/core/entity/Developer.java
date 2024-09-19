package com.example.demo.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"developerInfo", "projects"})
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "developer_infos_id", unique = true)
    private DeveloperInfo developerInfo;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Project> projects;

    public Developer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
