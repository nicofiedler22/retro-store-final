package cl.duoc.pagos.controller;

import cl.duoc.pagos.dto.ApiResponse;
import cl.duoc.pagos.dto.PagoRequestDTO;
import cl.duoc.pagos.dto.PagoResponseDTO;
import cl.duoc.pagos.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoService pagoService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<PagoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Listado de pagos", pagoService.listarTodos()));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<PagoResponseDTO>>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pagos del usuario", pagoService.listarPorUsuario(usuarioId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pago encontrado", pagoService.obtenerPorId(id)));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> iniciarPago(
            @Valid @RequestBody PagoRequestDTO dto) {
        PagoResponseDTO pago = pagoService.iniciarPago(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Transacción iniciada, pendiente de confirmación", pago));
    }

    @PostMapping("/confirmar/{token}")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> confirmarPago(@PathVariable String token) {
        PagoResponseDTO pago = pagoService.confirmarPago(token);
        String mensaje = switch (pago.getEstado()) {
            case APROBADO -> "Pago aprobado";
            case RECHAZADO -> "Pago rechazado por la pasarela";
            default -> "Pago procesado";
        };
        return ResponseEntity.ok(new ApiResponse<>(200, mensaje, pago));
    }

    @PostMapping("/{id}/anular")
    public ResponseEntity<ApiResponse<PagoResponseDTO>> anularPago(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Pago anulado correctamente", pagoService.anularPago(id)));
    }

}
