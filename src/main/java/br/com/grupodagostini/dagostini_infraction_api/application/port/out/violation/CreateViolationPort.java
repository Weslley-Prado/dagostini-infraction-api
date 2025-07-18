package br.com.grupodagostini.dagostini_infraction_api.application.port.out.violation;

import br.com.grupodagostini.dagostini_infraction_api.application.domain.model.Violation;

public interface CreateViolationPort {
    Violation createViolation(Violation violation);
}