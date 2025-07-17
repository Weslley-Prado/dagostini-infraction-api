package br.com.grupodagostini.dagostini_infraction_api.application.port.in;

import br.com.grupodagostini.dagostini_infraction_api.domain.model.Violation;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ViolationService {
    Violation createViolation(Violation violation);
    Optional<Violation> findViolationById(Long id);
    List<Violation> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to);
}