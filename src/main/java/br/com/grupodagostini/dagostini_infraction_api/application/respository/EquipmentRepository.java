package br.com.grupodagostini.dagostini_infraction_api.application.respository;


import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    Optional<Equipment> findBySerial(String serial);
}
