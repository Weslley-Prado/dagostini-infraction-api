package br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation;

import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindViolationsByEquipmentSerialUseCase {
    List<ViolationOutbound> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to);
}
