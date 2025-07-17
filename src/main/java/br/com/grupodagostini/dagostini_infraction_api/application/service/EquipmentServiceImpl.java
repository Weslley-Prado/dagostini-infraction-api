package br.com.grupodagostini.dagostini_infraction_api.application.service;

import br.com.grupodagostini.dagostini_infraction_api.application.port.in.EquipmentService;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.EquipmentRepository;
import br.com.grupodagostini.dagostini_infraction_api.domain.model.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Override
    public Equipment createEquipment(Equipment equipment) {
        if (equipmentRepository.findBySerial(equipment.getSerial()).isPresent()) {
            throw new IllegalStateException("Equipment with serial " + equipment.getSerial() + " already exists");
        }
        return equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> findEquipmentBySerial(String serial) {
        return equipmentRepository.findBySerial(serial);
    }
}