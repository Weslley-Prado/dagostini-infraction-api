package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.EquipmentService;
import br.com.grupodagostini.dagostini_infraction_api.application.port.in.ViolationService;
import br.com.grupodagostini.infraction.api.DefaultApi;
import br.com.grupodagostini.infraction.model.EquipmentDTO;
import br.com.grupodagostini.infraction.model.ViolationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SystemManagementInfringementController implements DefaultApi {

    private final EquipmentService equipmentService;

    private final ViolationService violationService;

    @Override
    public ResponseEntity<EquipmentDTO> createEquipment(EquipmentDTO equipmentDTO) {
        return DefaultApi.super.createEquipment(equipmentDTO);
    }

    @Override
    public ResponseEntity<EquipmentDTO> findEquipmentBySerial(String serial) {
        return DefaultApi.super.findEquipmentBySerial(serial);
    }

    @Override
    public ResponseEntity<List<EquipmentDTO>> listEquipments() {
        return DefaultApi.super.listEquipments();
    }

    @Override
    public ResponseEntity<ViolationDTO> createViolation(ViolationDTO violationDTO) {
        return DefaultApi.super.createViolation(violationDTO);
    }

    @Override
    public ResponseEntity<ViolationDTO> findViolationById(Long id) {
        return DefaultApi.super.findViolationById(id);
    }

    @Override
    public ResponseEntity<List<ViolationDTO>> listViolationsByEquipment(String serial, Date from, Date to) {
        return DefaultApi.super.listViolationsByEquipment(serial, from, to);
    }
}
