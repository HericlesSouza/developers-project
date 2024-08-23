package com.example.demo.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "developer_infos")
public class DeveloperInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "developer_since")
    @NotNull
    private LocalDate developerSince;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @NotNull
    @Column(name = "preferred_os")
    private OSEnum preferredOS;

    @OneToOne(mappedBy = "developerInfo", optional = false)
    private Developer developer;

    public DeveloperInfo(LocalDate developerSince, OSEnum preferredOS, Developer developer) {
        this.developerSince = developerSince;
        this.preferredOS = preferredOS;
        this.developer = developer;
    }
}
