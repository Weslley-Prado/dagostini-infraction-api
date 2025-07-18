package br.com.grupodagostini.dagostini_infraction_api.adapter.in.web.controller;

import br.com.grupodagostini.infraction.api.AuthenticationApi;
import br.com.grupodagostini.infraction.model.LoginRequest;
import br.com.grupodagostini.infraction.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthViolationManagerController implements AuthenticationApi {
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return AuthenticationApi.super.login(loginRequest);
    }
}