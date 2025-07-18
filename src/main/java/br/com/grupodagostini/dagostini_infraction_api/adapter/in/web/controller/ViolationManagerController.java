package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.controller;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation.ViolationInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.mapper.violation.ViolationInboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.mapper.ViolationOutboundMapper;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.ImageStorageUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.CreateViolationUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationByIdUseCase;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation.FindViolationsByEquipmentSerialUseCase;
import br.com.grupodagostini.dagostini_infraction_api.utils.DateTimeMapper;
import br.com.grupodagostini.infraction.api.ConsultViolationByEquipmentSerialApi;
import br.com.grupodagostini.infraction.api.ConsultViolationByIdApi;
import br.com.grupodagostini.infraction.api.RegistryViolationApi;
import br.com.grupodagostini.infraction.model.ViolationRequestRepresentation;
import br.com.grupodagostini.infraction.model.ViolationResponseRepresentation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ViolationManagerController implements  RegistryViolationApi, ConsultViolationByIdApi, ConsultViolationByEquipmentSerialApi{


    private final CreateViolationUseCase createViolationUseCase;
    private final FindViolationByIdUseCase findViolationByIdUseCase;
    private final FindViolationsByEquipmentSerialUseCase findViolationsByEquipmentSerialUseCase;
    private final ViolationInboundMapper violationInboundMapper;
    private final ViolationOutboundMapper violationOutboundMapper;
    private final DateTimeMapper dateTimeMapper;
    private final ImageStorageUseCase imageStorageUseCase;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return RegistryViolationApi.super.getRequest();
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