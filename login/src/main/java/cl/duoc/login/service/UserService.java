package cl.duoc.login.service;

import cl.duoc.login.dto.AutenticadorUserDTO;
import cl.duoc.login.dto.UserCredentialsDTO;
import cl.duoc.login.security.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    public UserService(WebClient webClient, JwtUtil jwtUtil) {
        this.webClient = webClient;
        this.jwtUtil = jwtUtil;
    }

    public String login(UserCredentialsDTO dto) {

        AutenticadorUserDTO usuario = webClient.get()
                .uri("/api/v1/autenticadores/correo/{correo}", dto.getCorreo())
                .retrieve()
                .bodyToMono(AutenticadorUserDTO.class)
                .block();

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!usuario.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(usuario.getCorreo());
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}