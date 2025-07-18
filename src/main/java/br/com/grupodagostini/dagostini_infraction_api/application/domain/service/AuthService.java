package br.com.grupodagostini.dagostini_infraction_api.application.domain.service;

import br.com.grupodagostini.dagostini_infraction_api.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(String username, String password) {
        // Exemplo simples com usuário fixo
        if ("admin".equals(username) && "123456".equals(password)) {
            return jwtTokenProvider.generateToken(username);
        }

        throw new RuntimeException("Credenciais inválidas");
    }
}