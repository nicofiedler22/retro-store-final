package cl.duoc.autenticador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {

    private Long id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(min = 3, max = 30, message = "El nombre del rol debe tener entre 3 y 30 caracteres")
    private String nombre;
}