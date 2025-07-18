package br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment;

import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;

import java.util.Optional;

public interface FindEquipmentBySerialUseCase {
    EquipmentOutbound findEquipmentBySerial(String serial);
}
