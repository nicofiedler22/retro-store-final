package cl.duoc.pagos.dto;

import cl.duoc.pagos.model.EstadoPago;
import cl.duoc.pagos.model.MetodoPago;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PagoResponseDTO {

    private Long id;
    private Long usuarioId;
    private String ordenReferencia;
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private EstadoPago estado;
    private String tokenTransaccion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
