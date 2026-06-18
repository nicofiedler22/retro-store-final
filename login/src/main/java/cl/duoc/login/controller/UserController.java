package cl.duoc.login.controller;

import cl.duoc.login.dto.ApiResponse;
import cl.duoc.login.dto.UserCreateDTO;
import cl.duoc.login.dto.UserCredentialsDTO;
import cl.duoc.login.dto.UserDTO;
import cl.duoc.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody UserCreateDTO dto) {
        UserDTO user = userService.createUser(dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Usuario registrado correctamente", user)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody UserCredentialsDTO dto) {
        String token = userService.login(dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Login exitoso", token)
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<String>> validateToken(@RequestParam String token) {
        boolean valid = userService.validateToken(token);

        if (valid) {
            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Token válido", "OK")
            );
        }

        return ResponseEntity.status(401).body(
                new ApiResponse<>(401, "Token inválido", null)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Listado de usuarios", users)
        );
    }
}