package br.com.grupodagostini.dagostini_infraction_api.application.domain.service;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation.ViolationInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.CreateViolationUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationByIdUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationsByEquipmentSerialUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViolationService implements CreateViolationUseCase, FindViolationByIdUseCase, FindViolationsByEquipmentSerialUseCase {

    @Override
    public ViolationOutbound createViolation(ViolationInbound violationInbound) {
        return null;
    }

    @Override
    public ViolationOutbound findViolationById(Long id) {
        return null;
    }

    @Override
    public List<ViolationOutbound> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to) {
        return List.of();
    }

//    @Override
//    public Violation createViolation(Violation violation) {
//        var equipment = equipmentRepository.findBySerial(violation.getEquipmentSerial())
//                .orElseThrow(() -> new NoSuchElementException("Equipment not found: " + violation.getEquipmentSerial()));
//        if (!equipment.getActive()) {
//            throw new IllegalStateException("Cannot register violation for inactive equipment: " + violation.getEquipmentSerial());
//        }
//        if (violation.getType() == ViolationType.VELOCITY) {
//            if (violation.getMeasuredSpeed() <= 0 || violation.getConsideredSpeed() <= 0 || violation.getRegulatedSpeed() <= 0) {
//                throw new IllegalStateException("Speed fields must be positive for Velocity violation");
//            }
//        }
//        return violationRepository.save(violation);
//    }
//
//    @Override
//    public Optional<Violation> findViolationById(Long id) {
//        return violationRepository.findById(id);
//    }
//
//    @Override
//    public List<Violation> findViolationsByEquipmentSerial(String serial, OffsetDateTime from, OffsetDateTime to) {
//        if (from != null && to != null) {
//            return violationRepository.findByEquipmentSerialAndOccurrenceDateUtcBetween(serial, from, to);
//        }
//        return violationRepository.findByEquipmentSerial(serial);
//    }
}