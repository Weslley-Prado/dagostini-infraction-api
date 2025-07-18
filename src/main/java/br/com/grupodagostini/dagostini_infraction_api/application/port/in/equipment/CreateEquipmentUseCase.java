package br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment.EquipmentInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;

public interface CreateEquipmentUseCase {
    EquipmentOutbound createEquipment(EquipmentInbound equipmentInbound);
}
