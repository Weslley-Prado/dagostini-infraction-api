package br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper;

import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;
import br.com.grupodagostini.dagostini_infraction_api.utils.DateTimeMapper;
import br.com.grupodagostini.infraction.model.ViolationResponseRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ViolationOutboundMapper {

    @Mapping(source = "occurrenceDateUtc", target = "occurrenceDateUtc", qualifiedByName = "offsetDateTimeToDate")
    ViolationResponseRepresentation toViolationResponseRepresentation(ViolationOutbound violationOutbound);

    List<ViolationResponseRepresentation> toViolationResponseRepresentation(List<ViolationOutbound> violationOutboundList);
}