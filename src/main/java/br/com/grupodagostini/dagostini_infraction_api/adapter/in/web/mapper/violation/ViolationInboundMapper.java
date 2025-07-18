package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.violation;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation.ViolationInbound;
import br.com.grupodagostini.infraction.model.ViolationRequestRepresentation;
import br.com.grupodagostini.dagostini_infraction_api.utils.DateTimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface ViolationInboundMapper {

    @Mapping(source = "occurrenceDateUtc", target = "occurrenceDateUtc", qualifiedByName = "dateToOffsetDateTime")
    ViolationInbound toViolationInbound(ViolationRequestRepresentation violationRequestRepresentation);
}