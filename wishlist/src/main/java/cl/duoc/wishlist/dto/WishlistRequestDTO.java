package cl.duoc.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WishlistRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 150, message = "El nombre del producto no puede superar los 150 caracteres")
    private String nombreProducto;

    @PositiveOrZero(message = "El precio de referencia no puede ser negativo")
    private BigDecimal precioReferencia;

}
