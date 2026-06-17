package cl.duoc.login.service;

import cl.duoc.login.dto.UserCreateDTO;
import cl.duoc.login.dto.UserCredentialsDTO;
import cl.duoc.login.dto.UserDTO;
import cl.duoc.login.model.User;
import cl.duoc.login.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserCreateDTO dto) {

        User user = new User();
        user.setCorreo(dto.getCorreo());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getId(),
                savedUser.getCorreo()
        );
    }

    public UserDTO login(UserCredentialsDTO dto) {

        User user = userRepository.findByCorreo(dto.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new UserDTO(
                user.getId(),
                user.getCorreo()
        );
    }
}