package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.equipment;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment.EquipmentInbound;
import br.com.grupodagostini.infraction.model.EquipmentRequestRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentInboundMapper {
    EquipmentInbound toEquipmentInbound(EquipmentRequestRepresentation equipmentRequestRepresentation);
}
