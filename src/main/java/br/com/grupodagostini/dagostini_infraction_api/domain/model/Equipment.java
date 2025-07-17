package br.com.grupodagostini.dagostini_infraction_api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "equipment")
@Data
public class Equipment {
    @Id
    @NotBlank
    @Size(max = 50)
    private String serial;

    @NotBlank
    @Size(max = 100)
    private String model;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private Double latitude;

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private Double longitude;

    @NotNull
    private Boolean active = true;
}