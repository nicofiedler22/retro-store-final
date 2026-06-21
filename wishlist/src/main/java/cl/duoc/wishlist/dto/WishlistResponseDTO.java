package cl.duoc.wishlist.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class WishlistResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long productoId;
    private String nombreProducto;
    private BigDecimal precioReferencia;
    private LocalDateTime fechaAgregado;

}
