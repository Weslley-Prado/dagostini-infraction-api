package br.com.grupodagostini.dagostini_infraction_api.application.service;

import br.com.grupodagostini.dagostini_infraction_api.application.port.in.ViolationService;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.EquipmentRepository;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.ViolationRepository;
import br.com.grupodagostini.dagostini_infraction_api.domain.model.Violation;
import br.com.grupodagostini.dagostini_infraction_api.domain.model.ViolationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository violationRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    public Violation createViolation(Violation violation) {
        var equipment = equipmentRepository.findBySerial(violation.getEquipmentSerial())
                .orElseThrow(() -> new NoSuchElementException("Equipment not found: " + violation.getEquipmentSerial()));
        if (!equipment.getActive()) {
            throw new IllegalStateException("Cannot register violation for inactive equipment: " + violation.getEquipmentSerial());
        }
        if (violation.getType() == ViolationType.VELOCITY) {
            if (violation.getMeasuredSpeed() <= 0 || violation.getConsideredSpeed() <= 0 || violation.getRegulatedSpeed() <= 0) {
                throw new IllegalStateException("Speed fields must be positive for Velocity violation");
            }
        }
        return violationRepository.save(violation);
    }


    @Override
    public Optional<Violation> findViolationById(Long id) {
        return violationRepository.findById(id);
    }

    @Override
    public List<Violation> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to) {
        if (from != null && to != null) {
            return violationRepository.findByEquipmentSerialAndOccurrenceDateUtcBetween(serial, from, to);
        }
        return violationRepository.findByEquipmentSerial(serial);
    }
}