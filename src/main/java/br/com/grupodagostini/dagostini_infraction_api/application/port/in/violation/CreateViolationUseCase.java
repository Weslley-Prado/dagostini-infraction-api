package br.com.grupodagostini.dagostini_infraction_api.application.port.in.violation;

import br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.bound.violation.ViolationInbound;
import br.com.grupodagostini.dagostini_infraction_api.adapter.out.bound.violation.ViolationOutbound;

public interface CreateViolationUseCase {
    ViolationOutbound createViolation(ViolationInbound violationInbound);
}
