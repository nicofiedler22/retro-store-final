package cl.duoc.pagos.service;

import cl.duoc.pagos.dto.PagoRequestDTO;
import cl.duoc.pagos.dto.PagoResponseDTO;
import cl.duoc.pagos.model.EstadoPago;
import cl.duoc.pagos.model.Pago;
import cl.duoc.pagos.repository.PagoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final SecureRandom random = new SecureRandom();

    public List<PagoResponseDTO> listarTodos() {
        return pagoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<PagoResponseDTO> listarPorUsuario(Long usuarioId) {
        return pagoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PagoResponseDTO obtenerPorId(Long id) {
        return toResponseDTO(buscarPagoPorId(id));
    }

    public PagoResponseDTO iniciarPago(PagoRequestDTO dto) {
        Pago pago = Pago.builder()
                .usuarioId(dto.getUsuarioId())
                .ordenReferencia(dto.getOrdenReferencia())
                .monto(dto.getMonto())
                .metodoPago(dto.getMetodoPago())
                .estado(EstadoPago.PENDIENTE)
                .tokenTransaccion(generarToken())
                .build();

        return toResponseDTO(pagoRepository.save(pago));
    }

    public PagoResponseDTO confirmarPago(String token) {
        Pago pago = pagoRepository.findByTokenTransaccion(token)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una transacción con el token indicado"));

        if (pago.getEstado() != EstadoPago.PENDIENTE) {
            throw new IllegalStateException(
                    "La transacción ya fue resuelta anteriormente (estado actual: " + pago.getEstado() + ")");
        }

        boolean aprobado = random.nextInt(100) < 90;
        pago.setEstado(aprobado ? EstadoPago.APROBADO : EstadoPago.RECHAZADO);

        return toResponseDTO(pagoRepository.save(pago));
    }

    public PagoResponseDTO anularPago(Long id) {
        Pago pago = buscarPagoPorId(id);

        if (pago.getEstado() == EstadoPago.ANULADO) {
            throw new IllegalStateException("La transacción ya se encuentra anulada");
        }
        if (pago.getEstado() == EstadoPago.RECHAZADO) {
            throw new IllegalStateException("No es posible anular una transacción rechazada");
        }

        pago.setEstado(EstadoPago.ANULADO);
        return toResponseDTO(pagoRepository.save(pago));
    }

    private Pago buscarPagoPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un pago con id " + id));
    }

    private String generarToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private PagoResponseDTO toResponseDTO(Pago pago) {
        return PagoResponseDTO.builder()
                .id(pago.getId())
                .usuarioId(pago.getUsuarioId())
                .ordenReferencia(pago.getOrdenReferencia())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .estado(pago.getEstado())
                .tokenTransaccion(pago.getTokenTransaccion())
                .fechaCreacion(pago.getFechaCreacion())
                .fechaActualizacion(pago.getFechaActualizacion())
                .build();
    }

}
