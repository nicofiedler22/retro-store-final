package cl.duoc.inventario.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder

public class InventarioResponseDTO {

    private Long id;
    private Long productoId;
    private String nombre;
    private Integer cantidad;
    private Integer stockMinimo;
    private String ubicacion;
    private Boolean activo;
    private Boolean bajoPorStockMinimo;
    private LocalDateTime fechaActualizacion;

}
