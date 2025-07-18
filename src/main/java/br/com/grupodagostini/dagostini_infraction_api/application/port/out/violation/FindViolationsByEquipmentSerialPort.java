package br.com.grupodagostini.dagostini_infraction_api.application.port.out.violation;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Violation;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindViolationsByEquipmentSerialPort {
    List<Violation> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to);
}