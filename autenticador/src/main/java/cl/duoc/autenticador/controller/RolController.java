package cl.duoc.autenticador.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.autenticador.dto.ApiResponse;
import cl.duoc.autenticador.model.Rol;
import cl.duoc.autenticador.service.RolService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Rol>>> getAllRoles() {

        List<Rol> roles = rolService.findAll();

        ApiResponse<List<Rol>> response =
                new ApiResponse<>(200, "Listado de roles", roles);

        return ResponseEntity.ok(response);
    }
}