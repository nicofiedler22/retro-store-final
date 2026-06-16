package cl.duoc.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class InventarioRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidad;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;

    private String ubicacion;
    private Boolean activo = true;

}
