package br.com.grupodagostini.dagostini_infraction_api.adapter.out.database.postgres;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Equipment;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment.CreateEquipmentPort;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment.FindAllEquipmentPort;
import br.com.grupodagostini.dagostini_infraction_api.application.port.out.equipment.FindEquipmentBySerialPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Slf4j
@Component
@AllArgsConstructor
public class EquipmentPersistenceAdapter implements CreateEquipmentPort, FindAllEquipmentPort, FindEquipmentBySerialPort {

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return null;
    }

    @Override
    public List<Equipment> findAllEquipments() {
        return List.of();
    }

    @Override
    public Optional<Equipment> findEquipmentBySerial(String serial) {
        return Optional.empty();
    }
}