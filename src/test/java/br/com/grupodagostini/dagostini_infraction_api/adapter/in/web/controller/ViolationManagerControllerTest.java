//package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.controller;
//
//import br.com.grupodagostini.infraction.model.ViolationRequestRepresentation;
//import br.com.grupodagostini.infraction.model.ViolationResponseRepresentation;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class ViolationManagerControllerTest {
//
//    @Autowired
//    private ViolationManagerController controller;
//
//    @Test
//    void testCreateViolation() throws Exception {
//        ViolationRequestRepresentation request = new ViolationRequestRepresentation();
//        request.setEquipmentSerial("123");
//        request.setOccurrenceDateUtc(new Date());
//        request.setType(ViolationRequestRepresentation.TypeEnum.VELOCITY);
//        request.setMeasuredSpeed(BigDecimal.valueOf(50.0));
//        request.setConsideredSpeed(BigDecimal.valueOf(50.0));
//        request.setRegulatedSpeed(BigDecimal.valueOf(40.0));
//
//        MockMultipartFile picture = new MockMultipartFile(
//                "picture", "test.jpg", "image/jpeg", "test image".getBytes()
//        );
//
//        ResponseEntity<ViolationResponseRepresentation> response = controller.createViolation(request, picture);
//
//        assertEquals(201, response.getStatusCodeValue());
//        assertNotNull(response.getHeaders().getLocation());
//        assertTrue(response.getHeaders().getLocation().toString().startsWith("/violations/"));
//        assertNotNull(response.getBody());
//        assertNotNull(response.getBody().getPicture());
//        assertTrue(response.getBody().getPicture().startsWith("http://localhost:8080/uploads/violations/"));
//    }
//}