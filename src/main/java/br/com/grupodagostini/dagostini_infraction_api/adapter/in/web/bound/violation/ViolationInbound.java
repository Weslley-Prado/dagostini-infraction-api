package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record ViolationInbound(
        @NotBlank
        @Size(max = 50)
        @Column(name = "equipment_serial")
        String equipmentSerial,

        @NotNull
        OffsetDateTime occurrenceDateUtc,

        @DecimalMin("0.0")
        Double measuredSpeed,

        @DecimalMin("0.0")
        Double consideredSpeed,

        @DecimalMin("0.0")
        Double regulatedSpeed,

        @NotBlank
        @Size(max = 1048576)
        String picture,

        @NotNull
        @Enumerated(EnumType.STRING)
        ViolationType type
) {

    public String getEquipmentSerial() {
        return equipmentSerial();
    }

    public ViolationType getType() {
        return type();
    }

    public Double getMeasuredSpeed() {
        return measuredSpeed();
    }

    public Double getConsideredSpeed() {
        return consideredSpeed();
    }

    public Double getRegulatedSpeed() {
        return regulatedSpeed();
    }
}