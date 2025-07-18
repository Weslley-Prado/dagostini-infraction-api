package br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment;

import lombok.Builder;

@Builder
public record EquipmentOutbound(
        String serial,
        String model,
        String address,
        Double latitude,
        Double longitude,
        Boolean active
) {
    public EquipmentOutbound {
        if (active == null) {
            active = true;
        }
    }
}