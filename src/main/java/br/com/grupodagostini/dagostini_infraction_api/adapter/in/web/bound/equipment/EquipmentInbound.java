package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EquipmentInbound(
        @NotBlank
        @Size(max = 50)
        String serial,

        @NotBlank
        @Size(max = 100)
        String model,

        @NotBlank
        @Size(max = 255)
        String address,

        @NotNull
        @DecimalMin("-90.0")
        @DecimalMax("90.0")
        Double latitude,

        @NotNull
        @DecimalMin("-180.0")
        @DecimalMax("180.0")
        Double longitude,

        @NotNull
        Boolean active
) {
    public EquipmentInbound {
        if (active == null) {
            active = true;
        }
    }
}