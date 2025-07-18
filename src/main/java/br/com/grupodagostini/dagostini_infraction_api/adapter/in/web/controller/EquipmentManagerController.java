package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.controller;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment.EquipmentInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.equipment.EquipmentInboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper.EquipmentOutboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.CreateEquipmentUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindAllEquipmentsUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindEquipmentBySerialUseCase;
import br.com.grupodagostini.infraction.api.ConsultEquipmentApi;
import br.com.grupodagostini.infraction.api.ConsultEquipmentBySerialApi;
import br.com.grupodagostini.infraction.api.RegistryEquipmentApi;
import br.com.grupodagostini.infraction.model.EquipmentRequestRepresentation;
import br.com.grupodagostini.infraction.model.EquipmentResponseRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EquipmentManagerController implements ConsultEquipmentApi,
        ConsultEquipmentBySerialApi,
        RegistryEquipmentApi{

    private final CreateEquipmentUseCase createEquipmentUseCase;
    private final FindAllEquipmentsUseCase findAllEquipmentsUseCase;
    private final FindEquipmentBySerialUseCase findEquipmentBySerial;
    private final EquipmentInboundMapper equipmentInboundMapper;
    private final EquipmentOutboundMapper equipmentOutboundMapper;


    @Override
    public ResponseEntity<EquipmentResponseRepresentation> createEquipment(EquipmentRequestRepresentation equipmentRequestRepresentation) {
        EquipmentInbound equipmentInbound = equipmentInboundMapper.toEquipmentInbound(equipmentRequestRepresentation);
        EquipmentOutbound registryEquipment = createEquipmentUseCase.createEquipment(equipmentInbound);
        URI location = URI.create("/equipments/" + registryEquipment.serial());
        return ResponseEntity.created(location).body(equipmentOutboundMapper.toEquipmentResponseRepresentation(registryEquipment));
    }

    @Override
    public ResponseEntity<EquipmentResponseRepresentation> findEquipmentBySerial(String serial) {
        EquipmentOutbound equipmentOutbound = findEquipmentBySerial.findEquipmentBySerial(serial);
        return ResponseEntity.ok(equipmentOutboundMapper.toEquipmentResponseRepresentation(equipmentOutbound));
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ConsultEquipmentApi.super.getRequest();
    }

    @Override
    public ResponseEntity<List<EquipmentResponseRepresentation>> listEquipments() {
        List<EquipmentOutbound> equipmentOutboundList = findAllEquipmentsUseCase.findAllEquipments();
        return ResponseEntity.ok(equipmentOutboundMapper.toListEquipmentResponseRepresentation(equipmentOutboundList));
    }
}