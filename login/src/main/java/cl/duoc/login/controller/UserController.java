package cl.duoc.login.controller;

import cl.duoc.login.dto.ApiResponse;
import cl.duoc.login.dto.UserCreateDTO;
import cl.duoc.login.dto.UserCredentialsDTO;
import cl.duoc.login.dto.UserDTO;
import cl.duoc.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(
            @Valid @RequestBody UserCreateDTO dto) {

        UserDTO user = userService.createUser(dto);

        return new ApiResponse<>(
                201,
                "Usuario registrado correctamente",
                user
        );
    }

    @PostMapping("/login")
    public ApiResponse<UserDTO> login(
            @Valid @RequestBody UserCredentialsDTO dto) {

        UserDTO user = userService.login(dto);

        return new ApiResponse<>(
                200,
                "Login exitoso",
                user
        );
    }
}