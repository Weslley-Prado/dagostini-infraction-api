package br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Equipment;

public interface CreateEquipmentPort {
    Equipment createEquipment(Equipment equipment);
}