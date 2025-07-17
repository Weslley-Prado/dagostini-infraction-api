package br.com.grupodagostini.dagostini_infraction_api.application.port.in;

import br.com.grupodagostini.dagostini_infraction_api.domain.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);
    List<Equipment> findAllEquipments();
    Optional<Equipment> findEquipmentBySerial(String serial);
}