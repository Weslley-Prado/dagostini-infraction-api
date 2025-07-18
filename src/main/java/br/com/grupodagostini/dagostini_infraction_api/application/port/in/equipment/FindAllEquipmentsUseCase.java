package br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment;

import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;

import java.util.List;

public interface FindAllEquipmentsUseCase {
    List<EquipmentOutbound> findAllEquipments();
}
