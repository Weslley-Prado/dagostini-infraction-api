package br.com.grupodagostini.dagostini_infraction_api.application.port.out.violation;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Violation;

import java.util.Optional;

public interface FindViolationByIdPort {
    Optional<Violation> findViolationById(Long id);
}