package br.com.grupodagostini.dagostini_infraction_api.application.port.out;

import br.com.grupodagostini.dagostini_infraction_api.domain.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
    List<Violation> findByEquipmentSerial(String serial);
    List<Violation> findByEquipmentSerialAndOccurrenceDateUtcBetween(String serial, OffsetDateTime from, OffsetDateTime to);
}