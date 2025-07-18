package br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper;

import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;
import org.mapstruct.Mapper;
import br.com.grupodagostini.infraction.model.EquipmentResponseRepresentation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentOutboundMapper {
    EquipmentResponseRepresentation toEquipmentResponseRepresentation(EquipmentOutbound equipmentOutbound);

    List<EquipmentResponseRepresentation> toListEquipmentResponseRepresentation(List<EquipmentOutbound> listEquipmentOutbound);

}
