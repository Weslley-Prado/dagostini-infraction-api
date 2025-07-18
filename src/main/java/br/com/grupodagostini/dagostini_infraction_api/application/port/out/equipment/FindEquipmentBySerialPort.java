package br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Equipment;

import java.util.Optional;

public interface FindEquipmentBySerialPort {
    Optional<Equipment> findEquipmentBySerial(String serial);
}