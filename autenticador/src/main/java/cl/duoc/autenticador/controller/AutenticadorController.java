package cl.duoc.autenticador.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.autenticador.dto.ApiResponse;
import cl.duoc.autenticador.model.Autenticador;
import cl.duoc.autenticador.service.AutenticadorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/autenticadores")
public class AutenticadorController {

    private final AutenticadorService autenticadorService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Autenticador>>> getAllAutenticadores() {
        List<Autenticador> autenticadores = autenticadorService.findAll();

        ApiResponse<List<Autenticador>> response =
                new ApiResponse<>(200, "Listado de usuarios", autenticadores);

        return ResponseEntity.ok(response);
    }
}