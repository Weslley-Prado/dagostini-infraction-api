package br.com.grupodagostini.dagostini_infraction_api.application.domain.service;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment.EquipmentInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.CreateEquipmentUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindAllEquipmentsUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindEquipmentBySerialUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService implements CreateEquipmentUseCase, FindAllEquipmentsUseCase, FindEquipmentBySerialUseCase {

    @Override
    public EquipmentOutbound createEquipment(EquipmentInbound equipmentInbound) {
        return null;
    }

    @Override
    public List<EquipmentOutbound> findAllEquipments() {
        return List.of();
    }

    @Override
    public EquipmentOutbound findEquipmentBySerial(String serial) {
        return null;
    }

}