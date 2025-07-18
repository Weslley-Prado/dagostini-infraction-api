package br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Equipment;

import java.util.List;

public interface FindAllEquipmentPort {
    List<Equipment> findAllEquipments();
}