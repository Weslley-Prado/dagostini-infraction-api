package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.controller;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.equipment.EquipmentInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation.ViolationInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.equipment.EquipmentInboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.violation.ViolationInboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.equipment.EquipmentOutbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper.EquipmentOutboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper.ViolationOutboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.ImageStorageUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.CreateEquipmentUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindAllEquipmentsUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.equipment.FindEquipmentBySerialUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.CreateViolationUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationByIdUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationsByEquipmentSerialUseCase;
import br.com.grupodagostini.dagostini_infraction_api.utils.DateTimeMapper;
import br.com.grupodagostini.infraction.api.DefaultApi;
import br.com.grupodagostini.infraction.model.EquipmentRequestRepresentation;
import br.com.grupodagostini.infraction.model.EquipmentResponseRepresentation;
import br.com.grupodagostini.infraction.model.ViolationRequestRepresentation;
import br.com.grupodagostini.infraction.model.ViolationResponseRepresentation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ViolationManagerController implements DefaultApi {

    private final CreateEquipmentUseCase createEquipmentUseCase;
    private final FindAllEquipmentsUseCase findAllEquipmentsUseCase;
    private final FindEquipmentBySerialUseCase findEquipmentBySerial;
    private final CreateViolationUseCase createViolationUseCase;
    private final FindViolationByIdUseCase findViolationByIdUseCase;
    private final FindViolationsByEquipmentSerialUseCase findViolationsByEquipmentSerialUseCase;
    private final EquipmentInboundMapper equipmentInboundMapper;
    private final EquipmentOutboundMapper equipmentOutboundMapper;
    private final ViolationInboundMapper violationInboundMapper;
    private final ViolationOutboundMapper violationOutboundMapper;
    private final DateTimeMapper dateTimeMapper;
    private final ImageStorageUseCase imageStorageUseCase;

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
    public ResponseEntity<List<EquipmentResponseRepresentation>> listEquipments() {
        List<EquipmentOutbound> equipmentOutboundList = findAllEquipmentsUseCase.findAllEquipments();
        return ResponseEntity.ok(equipmentOutboundMapper.toListEquipmentResponseRepresentation(equipmentOutboundList));
    }

    @Override
    public ResponseEntity<ViolationResponseRepresentation> createViolation(ViolationRequestRepresentation violationRequestRepresentation, MultipartFile picture) {
        // Validar campos condicionais para VELOCITY
        if (violationRequestRepresentation.getType() == ViolationRequestRepresentation.TypeEnum.VELOCITY) {
            if (violationRequestRepresentation.getMeasuredSpeed() == null ||
                    violationRequestRepresentation.getConsideredSpeed() == null ||
                    violationRequestRepresentation.getRegulatedSpeed() == null) {
                throw new IllegalArgumentException("measuredSpeed, consideredSpeed, and regulatedSpeed are required for VELOCITY type");
            }
        }

        // Fazer upload da imagem e obter URL
        String pictureUrl;
        try {
            pictureUrl = imageStorageUseCase.storeImage(picture);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to store image: " + e.getMessage());
        }

        // Definir a URL no request
        violationRequestRepresentation.setPicture(pictureUrl);

        ViolationInbound inbound = violationInboundMapper.toViolationInbound(violationRequestRepresentation);
        ViolationOutbound outbound = createViolationUseCase.createViolation(inbound);
        URI location = URI.create("/violations/" + outbound.id());
        return ResponseEntity.created(location).body(violationOutboundMapper.toViolationResponseRepresentation(outbound));
    }

    @Override
    public ResponseEntity<ViolationResponseRepresentation> findViolationById(@Positive Long id) {
        ViolationOutbound violationOutbound = findViolationByIdUseCase.findViolationById(id);
        return ResponseEntity.ok(violationOutboundMapper.toViolationResponseRepresentation(violationOutbound));
    }

    @Override
    public ResponseEntity<List<ViolationResponseRepresentation>> listViolationsByEquipment(String serial, Date from, Date to) {
        List<ViolationOutbound> violationOutboundList = findViolationsByEquipmentSerialUseCase
                .findViolationsByEquipmentSerial(serial, dateTimeMapper.dateToOffsetDateTime(from), dateTimeMapper.dateToOffsetDateTime(to));
        return ResponseEntity.ok(violationOutboundMapper.toViolationResponseRepresentation(violationOutboundList));
    }
}