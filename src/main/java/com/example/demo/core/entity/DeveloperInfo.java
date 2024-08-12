package com.example.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "developer_infos")
public class DeveloperInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "developer_since")
    @NotNull
    private LocalDate developerSince;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @NotNull
    @Column(name = "preferred_os")
    private OSEnum preferredOS;

    @OneToOne(mappedBy = "developerInfo", fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private Developer developer;

    public DeveloperInfo(LocalDate developerSince, OSEnum preferredOS, Developer developer) {
        this.developerSince = developerSince;
        this.preferredOS = preferredOS;
        this.developer = developer;
    }
}
