package br.com.grupodagostini.dagostini_infraction_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "violation")
@Data
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "equipment_serial")
    private String equipmentSerial;

    @NotNull
    private OffsetDateTime occurrenceDateUtc;

    @DecimalMin("0.0")
    private Double measuredSpeed;

    @DecimalMin("0.0")
    private Double consideredSpeed;

    @DecimalMin("0.0")
    private Double regulatedSpeed;

    @NotBlank
    @Size(max = 1048576) // 1MB
    private String picture;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ViolationType type;
}